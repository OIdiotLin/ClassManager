<?php

require_once 'applications/update.php';
require_once 'applications/personManage.php';
require_once 'utils/const.php';
require_once 'index.php';

function business($act) {
	switch ($act) {
		/**
		 * @return latest app version infomation
		 */
		case API_CHECK_UPDATE:
			askUpdate();
			break;
		/**
		 * @return url of new apk for updating
		 */
		case API_GET_APK_URL:
			getApkUrl();
			break;
		/**
		 * @return list of persons
		 */
		case API_GET_PERSON_LIST:
			getPersonList();	
			break;
		/**
		 * show index if without any recognized args
		 */
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