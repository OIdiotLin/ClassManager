<?php

require_once 'utils/response.php';
require_once 'AppVersionInfo.php';

function askUpdate() {
	$info = new AppVersionInfo(UPDATE_VERSION_CODE, UPDATE_VERSION_NAME, UPDATE_NEW_FEATURES);
	Response::show(200, 'OK', $info->toArray());
}

?>