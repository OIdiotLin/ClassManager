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
		return $this->versionCode;
	}

	public function setVersionName($versionName) {
		$this->versionName = $versionName;
	}

	public function getVersionName() {
		return $this->versionName;
	}

	public function setNewFeatures($newFeatures) {
		$this->newFeatures = $newFeatures;
	}

	public function getNewFeatures() {
		return $this->newFeatures;
	}

	public function toArray() {
		return array('versionCode' => $this->versionCode,
		             'versionName' => $this->versionName,
		             'newFeatures' => $this->newFeatures);
	}
}

?>