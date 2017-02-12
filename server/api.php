<?php

require_once 'utils/const.php';
require_once 'index.php';

function business($type) {
	switch ($type) {
		case constant('API_CHECK_UPDATE'):
			echo 'checking update';
			break;
		case constant('API_GET_APK_URL'):
			echo constant('NEW_APK_URL');
			break;
		default:
			show_index();
			break;
	}
}

var_dump($_GET);

if (array_key_exists('business', $_GET))
	business($_GET['business']);
else
	show_index();

?>