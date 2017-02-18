<?php

require_once 'Person.php';

function getPersonList() {
	$connection = mysql_connect(DB_HOST, DB_USER, DB_PWD);
	if ($connection == null) {
		die('Could not connect: '.DB_HOST);
	}
	mysql_select_db(DB_NAME, $connection);
	mysql_query("SET NAMES UTF8");

	$sql = "SELECT * from ".TBL_PERSONS." WHERE true";
	$result = mysql_query($sql);

	$list = array();

	while ($info = mysql_fetch_assoc($result)) {
		$person = array('person' => $info);
		array_push($list, $person);
	}

	Response::show(200, 'OK', $list);
}

?>