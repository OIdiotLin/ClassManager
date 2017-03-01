<?php

function getEvent() {
	$db_handler = mysql_connect(DB_HOST, DB_USER, DB_PWD);
	$handler = mysql_connect(DB_HOST, DB_USER, DB_PWD);
	mysql_select_db(DB_NAME);
	mysql_query("SET AUTOCOMMIT=0");	// unset auto-commit
	mysql_query("SET NAMES UTF8");
	mysql_query("BEGIN");	// start event
	$sql = "SELECT * from ".TBL_EVENTS." WHERE true";
	$result = mysql_query($sql);
	mysql_query("COMMIT");

	$list = array();

	while ($info = mysql_fetch_assoc($result)) {
		$event = array('event' => $info);
		array_push($list, $event);
	}

	Response::show(200, 'OK', $list);

	mysql_close($handler);
}

/**
 * 添加 Event，更新数据库
 *
 */
function addEvent() {

}

?>