# KAPEWARE CRM (Sprint 3 - Team 4)
## What is KAPEWARE CRM?
This is a web application for KAPEWARE (the sprint 1 enterprise).

## Requirements
Those versions were used during development:
- Apache Maven 3.6.1 (or just use mvnw.cmd for Windows / mvnw for Linux)
- MySQL 8.0.18
- This project

If you want to use another version, is up to you.

## Set up the project
1. First clone this repo:
```
git clone https://github.com/RamsayBoy/CRM-sprint3-ISI
```
2. For creating the database, run the following commands at the mysql prompt:
```
mysql> create database db_kapesystems;
mysql> create user 'kapesystems'@'%' identified by 'iwanttopassit';
mysql> grant all on db_kapesystems.* to 'kapesystems'@'%';
```

## Run the project
1. Go into the project root and run the following command:
```
mvn spring-boot:run
```
2. When everything had loaded, open your favourite browser and go to http://localhost:8080.
