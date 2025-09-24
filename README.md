Library Manager (Java Swing + JDBC/MySQL)

A desktop application built with Java Swing and JDBC–MySQL for corporate-style record and inventory needs. It manages Books and Members on a single screen, featuring a simple workflow, secure SQL calls, and a maintainable layered architecture.

✨ Features

Single-window management: add book, increase/decrease stock, delete, refresh list

Search model: query triggered by Enter (instead of live filter): name LIKE ? OR author LIKE ?

Case-insensitive search: utf8mb4_turkish_ci collation

Secure data access: all queries via PreparedStatement

Clean & controlled UI: JTable is read-only; updates via buttons/dialogs

Login & membership flow: LoginScreenUI, sign-up via “Become Member”

Note: (See Appendix Figure 12 – Books class with constructor and getters; Members class follows the same pattern.)

🧱 Architecture

Layers: UI (Swing) → (optional) Service/Flow → DAO/JDBC

DAOs: BooksDAO, MembersDAO (CRUD)

Model (POJO):

Books(id, name, author, page, stock)

Members(id, name, surname, email, username, password)

Fields are private; access via public getters. Objects are created via constructors.

🗄️ Database

Database name: my_library

Charset / Collation: utf8mb4 / utf8mb4_turkish_ci

Single SQL file: INTERNSHIPPROJECT.sql (all DDL + optional seed data)

Tables:

books: id (PK, AI), name, author, page, stock

members: id (PK, AI), name, surname, email (UNIQUE), username (UNIQUE), password

⚙️ Setup (No scripts — Minimal)

Command line (Windows, Linux, macOS):

mysql -u root -p -e "CREATE DATABASE IF NOT EXISTS my_library DEFAULT CHARSET utf8mb4 COLLATE utf8mb4_turkish_ci;"
mysql -u root -p my_library < INTERNSHIPPROJECT.sql


MySQL Workbench (GUI):

File → Open SQL Script… → open INTERNSHIPPROJECT.sql

In SCHEMAS, create/select my_library

Click Execute (⚡)

If your file name differs, use the same name in the commands.

🔗 Application Connection

In your DBUtil / configuration:

jdbc:mysql://localhost:3306/my_library?useUnicode=true&characterEncoding=utf8&useSSL=false


Set MySQL username/password according to your local setup.

▶️ Run

Run the Main class from your IDE, or

Package with Maven/Gradle and run the JAR.

🖱️ Usage

Startup: LoginScreenUI

Entry: username/password check

Become Member: register via MembersUI

Books screen:

+ Add Product, Stock Entry/Subtract Stock, Delete Product

Search (Enter): type book/author → press Enter

Refresh: reloads the table

🔐 Validation & Security (Summary)

Inputs are trimmed; empty/negative/non-numeric values are blocked.

Short, clear messages are shown on errors (e.g., “Invalid number.”).

Version note: In this first release, the password field is unmasked in UI and unhashed in DB.
Roadmap: JPasswordField in UI and BCrypt for hashing.
