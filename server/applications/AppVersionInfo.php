<?php

/**
* app 版本信息
*/
class AppVersionInfo {

	private $versionCode;	// 版本号
	private $versionName;	// 版本名
	private $newFeatures;	// 新特性

	function __construct($versionCode, $versionName, $newFeatures) {
		$this->versionCode = $versionCode;
		$this->versionName = $versionName;
		$this->newFeatures = $newFeatures;
	}

	public function setVersionCode($versionCode) {
		$this->versionCode = $versionCode;
	}

	public function getVersionCode() {
		return $versionCode;
	}

	public function setVersionName($versionName) {
		$this->versionName = $versionName;
	}

	public function getVersionName() {
		return $versionName;
	}

	public function setNewFeatures($newFeatures) {
		$this->newFeatures = $newFeatures;
	}

	public function getNewFeatures() {
		return $newFeatures;
	}

	public function toArray() {
		return array('versionCode' => $versionCode,
		             'versionName' => $versionName,
		             'newFeatures' => $newFeatures);
	}
}

?>