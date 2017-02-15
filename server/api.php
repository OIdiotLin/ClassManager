<?php

require_once 'applications/update.php';
require_once 'utils/const.php';
require_once 'index.php';

function business($act) {
	switch ($act) {
		case API_CHECK_UPDATE:
			askUpdate();	// return new version
			break;
		case API_GET_APK_URL:
			getApkUrl();	// return new apk file url
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