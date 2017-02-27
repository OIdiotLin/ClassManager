<?php

function getPersonList() {
	$handler = mysql_connect(DB_HOST, DB_USER, DB_PWD);
	mysql_select_db(DB_NAME);
	mysql_query("SET AUTOCOMMIT=0");	// unset auto-commit
	mysql_query("SET NAMES UTF8");
	mysql_query("BEGIN");	// start event
	$sql = "SELECT * from ".TBL_PERSONS." WHERE true";
	$result = mysql_query($sql);
	mysql_query("COMMIT");

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
 * 
 */
function addParticipation(&$personIdList, $deltaParticipation) {
	$db_handler = mysql_connect(DB_HOST, DB_USER, DB_PWD);
	mysql_select_db(DB_NAME);

	foreach ($personIdList as $value) {
		$originalParticipation = mysql_query("SELECT ".TBL_PERSON_PARTICIPATION.
		                                      " FROM ".TBL_PERSONS.
		                                      " WHERE ".TBL_PERSON_ID."=$value");
		$newParticipation = $originalParticipation + $deltaParticipation;
		mysql_query("UPDATE ".TBL_PERSONS.
		            " SET ".TBL_PERSON_PARTICIPATION."=$newParticipation".
		            " WHERE ".TBL_PERSON_ID."=$value");
	}
	mysql_close($db_handler);
}

?>