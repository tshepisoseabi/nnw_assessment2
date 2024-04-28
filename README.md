# Address Processor

The Address Processor is a Java application that processes address data from a JSON file and performs various operations such as pretty printing addresses, validating addresses, and determining the highest common factor of numeric postal codes.

## Features

- Pretty print an address: The application can format an address in a readable format.
- Validate addresses: Validate if an address contains necessary information such as a numeric postal code, a country, and at least one address line.
- Determine highest common factor: Calculate the highest common factor of numeric postal codes in the address data.
- Handle various address types: The application supports different types of addresses such as physical, postal, and business addresses.

## Usage

1. Import the project into your Java IDE.
2. Ensure you have the necessary dependencies configured (e.g., Jackson library for JSON processing).
3. Run the AddressProcessor class to see sample usage of the application features.

## Dependencies

- Jackson library for JSON processing (com.fasterxml.jackson.core:jackson-databind)
- JUnit for unit testing (org.junit.jupiter:junit-jupiter)

## File Structure

- `src/main/java`: Contains the main Java source files.
- `src/test/java`: Contains the unit test files.
- `C:/addresses.json`: Sample JSON file containing address data.
