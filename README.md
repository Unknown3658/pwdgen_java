# pwdgen — CSPRNG Password Generator in Java 17

Cryptographically secure password generator with no external dependencies.

## Disclaimer
I am not a developer, nor have I ever been one.
As a former SAST engineer and an information security professional, I wrote the program with a focus on security.
The program was developed using a local LLM оmnicoder-9b-q5_k_m on a llama.cpp + OpenCode stack.
The program passed all SonarQube security checks for C++.

## Development Principles
- **Confidentiality**: No logging, no history. A password is generated and disappears.
- **Integrity**: Cryptographically secure randomness only. No pseudo-random generators.
- **Availability**: One run — one password.

## Features
- **CSPRNG only** — uses Java's `SecureRandom`, not pseudo-random generators
- **Platform-specific entropy sources:**
  - Windows: `SecureRandom` with `RandomProvider`
  - macOS: `SecureRandom` with `SecureRandom`
  - Linux: `SecureRandom` with `SecureRandom`
- **Full character set control** with `-nl`, `-nu`, `-nd`, `-ns`

### Verified platforms
Windows; code for macOS and Linux has been added, but builds and functionality haven't been verified.
Anyway, who knows—test it out and let me know in the issues.

## Requirements

Language/Development Environment
- Java 17
- Maven 3.6+

## Building

### Windows
```bash
mvn clean package
```

### Linux/macOS
```bash
mvn clean package
```

## Testing

```bash
mvn test
# or with output:
mvn test -Dsurefire.failIfNoSpecs=true
```

## Usage

```bash
java -jar target/pwdgen-1.0.0.jar
```

### Common Scenarios

| Flag | Description | Default |
|------|-------------|---------|
| `-h, --help` | Show this help message | — |
| `-l N` | Password length (1 ≤ N ≤ 256) | 16 |
| `-c M` | Number of passwords (1 ≤ M ≤ 10000) | 1 |
| `-nl` | Exclude lowercase letters | enabled |
| `-nu` | Exclude uppercase letters | enabled |
| `-nd` | Exclude digits | enabled |
| `-ns` | Exclude symbols | enabled |

### Quick Start

```bash
# 16-character password with all character types
java -jar target/pwdgen-1.0.0.jar

# Show help
java -jar target/pwdgen-1.0.0.jar -h

# Only letters and symbols (no numbers)
java -jar target/pwdgen-1.0.0.jar -l 24 -nd

# Only uppercase letters and symbols
java -jar target/pwdgen-1.0.0.jar -l 20 -nl -nd -ns

# Only lowercase letters (no other types)
java -jar target/pwdgen-1.0.0.jar -l 32 -nu -nd -ns

# All types except symbols (letters + numbers)
java -jar target/pwdgen-1.0.0.jar -l 24 -ns

# Generate 5 passwords
java -jar target/pwdgen-1.0.0.jar -c 5

# Generate 24-character password
java -jar target/pwdgen-1.0.0.jar -l 24
```

## License

MIT License — see [LICENSE](LICENSE)

## Architecture

```
src/main/java/com/pwdgen/
├── Main.java              # entry point
├── cli/
│   ├── CliArgs.java       # CLI arguments data class
│   ├── CliException.java  # CLI exceptions
│   └── CliParser.java     # CLI parsing
├── charset/
│   ├── Charset.java            # charset management
│   └── CharsetBuilder.java     # charset builder
├── csprng/
│   ├── BaseCSPRNG.java     # CSPRNG base interface
│   ├── CSPRNG.java         # CSPRNG interface
│   ├── LinuxCSPRNG.java    # Linux CSPRNG
│   ├── MacCSPRNG.java      # macOS CSPRNG
│   ├── PlatformCSPRNG.java # platform-agnostic CSPRNG
│   └── WindowsCSPRNG.java  # Windows CSPRNG
└── password/
    ├── PasswordGenerator.java        # password generation
    └── PasswordValidationError.java  # validation errors

┌─────────┐     ┌────────────┐     ┌──────────┐
│ main()  │────>│CliParser   │────>│CliArgs   │
└─────────┘     └────────────┘     └────┬─────┘
                                       │
                                       ▼
                              ┌──────────────────┐
                              │  Charset builder │         
                              └────────┬─────────┘
                                       │
                                       ▼
                              ┌──────────────────┐
                              │PasswordGenerator │
                              │  generate()      │
                              └────────┬─────────┘
                                       │
                                       ▼
                              ┌──────────────────┐
                              │PlatformCSPRNG    │
                              │  create()        │
                              └────────┬─────────┘
                                       │
                    ┌──────────────────┼──────────────────┐
                    ▼                  ▼                  ▼
            ┌───────────────┐  ┌───────────────┐  ┌───────────────┐
            │ WindowsCSPRNG │  │LinuxCSPRNG    │  │MacCSPRNG      │
            │SecureRandom   │  │SecureRandom   │  │SecureRandom   │
            └───────┬───────┘  └───────┬───────┘  └───────┬───────┘
                    │                  │                  │
                    └──────────────────┼──────────────────┘
                                       │
                                       ▼
                              ┌───────────────┐
                              │  uint32_t     │
                              └───────┬───────┘
                                      │
                                      ▼
                              ┌───────────────┐
                              │   stdout      │
                              └───────────────┘
```
