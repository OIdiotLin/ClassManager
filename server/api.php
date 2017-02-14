<?php

require_once 'utils/const.php';
require_once 'index.php';

function business($type) {
	switch ($type) {
		case API_CHECK_UPDATE:
			checkUpdate();
			break;
		case API_GET_APK_URL:
			echo NEW_APK_URL;
			break;
		default:
			show_index();
			break;
	}
}

if (array_key_exists(API_ACT_NODE, $_GET))
	business($_GET[API_ACT_NODE]);
else
	show_index();

?>