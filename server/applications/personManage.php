<?php

require_once 'Person.php';

function getPersonList() {
	$handler = mysql_connect();
	mysql_select_db(DB_NAME);
	mysql_query("SET AUTOCOMMIT=0");	// unset auto-commit
	mysql_query("BEGIN")	// start event
	$sql = "SELECT * from ".TBL_PERSONS." WHERE true";
	$result = mysql_query($sql);

	$list = array();

	while ($info = mysql_fetch_assoc($result)) {
		$person = array('person' => $info);
		array_push($list, $person);
	}

	Response::show(200, 'OK', $list);

	mysql_close($handler);
}

/**
 * 修改数据库，为对应 id 的人增加 participation 值
 * @param _GET["id"]
 */
function addParticipation(&$personId, $deltaParticipation) {
	foreach ($id as $key => $value) {
		# code...
	}
	
}

?>