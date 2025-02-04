# FineFinance

## Description
A mock project for personal stock management.

## Features
- Stock Collection System. 
  - Periodically collecting stock data and updating the database; 
  - Utilizes xxl-job to provide a comprehensive task monitoring mechanism.  
- Domestic Index Service. 
  - Primarily collects real-time data on domestic market indices.  
- Sector Analysis Service. 
  - Mainly gathers market data for various domestic industry sectors.  
- Top Gainers Display Function. 
  - Ranks stocks based on their price increase and provides data on trending stocks.  
- Limit Up/Down Display Function. 
  - Tracks the number of stocks hitting their daily price limits (up or down).  
- Trading Volume Comparison Display Function. 
  - Compares and analyzes trends in trading volumes across the stock market.  
- Stock Details Display Function. 
  - Includes intraday quotes, daily K-line charts, weekly K-line charts, and stock description services.  
- Report Export Service. 
  - Exports data on trending stocks sorted by price increase.  
- Others. 
  - User information management; 
  - role management; 
  - permission management.

## Tech Stack
### System Components
- Frontend: vue-cli+vue+element+axios
- Backend: Java+SpringBoot+MyBatis
- Database: MySQL
- Cache: Redis
- Message Queue: RabbitMQ
- Security: Spring Security+JWT

## Database Design
- MySQL Docker Image: `mysql:8.0.33`
~~~bash
docker run --restart=always -p 3306:3306 --name mysql -v /tmp/mysql/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=root -e TZ=Asia/Shanghai -d mysql:8.0.33 --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci --default-time_zone='+8:00'
~~~
### ER Diagram
- Broad Market and Sector Correlation Table
  - `stock_market_index_info` Domestic Market Data Details Table
  - `Stock Outer Market Index Info` External Market Details Information Table
  - `Stock Block RT Info` Stock Sector Details Information Table

- Individual Stock Related Tables
  - `stock rt info` stock details table
  - `stock business` statement of operations

- User Permission Related Tables
  - `sys_user` user information table
  - `sys_role` role information table
  - `sys_permission` permission information table
  - `sys_user_role` user-role relationship table
  - `sys_role_permission` role-permission relationship table

- Logs
  - `sys_log` system log table

## Project Structure
- stock_parent
  - stock_common
  - stock_backend
  - stock_job
