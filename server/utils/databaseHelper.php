<?php

class databaseHelper {
	private $db_host;
	private $db_name;
	private $db_user;
	private $db_pwd;

	function __construct($host = DB_HOST,
	                     $name = DB_NAME,
	                     $user = DB_USER,
	                     $pwd = DB_PWD) {
		$this->db_host = $host;
		$this->db_name = $name;
		$this->db_user = $user;
		$this->db_pwd = $pwd;
	}

	/**
	 * 连接 MySQL 服务器
	 * @return connection
	 */
	public function connect() {
		return mysql_connect($this->db_host, $this->db_user, $this->db_pwd);
	}

	public function select_db($db_name = DB_NAME) {
		$connection = $this->connect();
		if ($connection == null) {
			die('Could not connect: '.DB_HOST);
		}
		mysql_select_db($db_name, $connection);
	}

	public function query($sql) {
		mysql_query("SET NAMES UTF8");
		return mysql_query($sql);
	}
}

?>