const { Pool } = require("pg");

const pool = new Pool({
  user: "postgres",       
  host: "localhost",      
  database: "loginpag",   
  password: "isaias018",
  port: 5432,             
});

module.exports = pool;
