<?php

require_once 'Person.php';

function getPersonList() {
	$dbHelper = new databaseHelper();
	$dbHelper->select_db(DB_NAME);
	$sql = "SELECT * from ".TBL_PERSONS." WHERE true";
	$result = $dbHelper->query($sql);

	$list = array();

	while ($info = mysql_fetch_assoc($result)) {
		$person = array('person' => $info);
		array_push($list, $person);
	}

	Response::show(200, 'OK', $list);
}

?>