# EMPLOYEE INVENTORY TRACKER

[![Build Status](https://travis-ci.org/khwilo/employee_manager.svg?branch=develop)](https://travis-ci.org/khwilo/employee_manager) [![Maintainability](https://api.codeclimate.com/v1/badges/d4cd75b483b1c47f5005/maintainability)](https://codeclimate.com/github/khwilo/employee_manager/maintainability) [![Test Coverage](https://api.codeclimate.com/v1/badges/d4cd75b483b1c47f5005/test_coverage)](https://codeclimate.com/github/khwilo/employee_manager/test_coverage)

Track issuance of items to employees and assign email accounts to them as well.

## Usage

1. Clone the repository:

```bash
$ git clone https://github.com/khwilo/employee_manager.git
```

2. Cd into the repository:

```bash
$ cd employee_manager
```

3. Run the application using the following commands:

```bash
$ mvn clean install
$ java -jar target/employee_manager-0.0.1-SNAPSHOT.jar 
```
## API ENDPOINTS

- **Register an employee** - `http://localhost:5000/api/v1/auth/register`
- **Login an employee** - `http://localhost:5000/api/v1/auth/login`
- **Fetch all employees** - `http://localhost:5000/api/v1/employees`
- **Create a role** - `http://localhost:5000/api/v1/role/create/{id}`
- **Fetch all employees with roles** - `http://localhost:5000/api/v1/employees/roles`
