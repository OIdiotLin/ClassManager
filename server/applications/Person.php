<?php

class Person {

	private $id;
	private $studentNumber;
	private $pinyin;
	private $gender;
	private $nativeProvince;
	private $dormitory;
	private $birthday;
	private $phoneNumber;
	private $position;
	private $participation;

	public function getId() {
	    return $this->id;
	}
	
	public function setId($id) {
	    $this->id = $id;
	    return $this;
	}
	
	public function getStudentNumber() {
	    return $this->studentNumber;
	}
	
	public function setStudentNumber($studentNumber) {
	    $this->studentNumber = $studentNumber;
	    return $this;
	}
	
	public function getPinyin() {
	    return $this->pinyin;
	}
	
	public function setPinyin($pinyin) {
	    $this->pinyin = $pinyin;
	    return $this;
	}
	
	public function getGender() {
	    return $this->gender;
	}
	
	public function setGender($gender) {
	    $this->gender = $gender;
	    return $this;
	}
	
	public function getNativeProvince() {
	    return $this->nativeProvince;
	}
	
	public function setNativeProvince($nativeProvince) {
	    $this->nativeProvince = $nativeProvince;
	    return $this;
	}
	
	public function getDormitory() {
	    return $this->dormitory;
	}
	
	public function setDormitory($dormitory) {
	    $this->dormitory = $dormitory;
	    return $this;
	}
	
	public function getBirthday() {
	    return $this->birthday;
	}
	
	public function setBirthday($birthday) {
	    $this->birthday = $birthday;
	    return $this;
	}
	
	public function getPhoneNumber() {
	    return $this->phoneNumber;
	}
	
	public function setPhoneNumber($phoneNumber) {
	    $this->phoneNumber = $phoneNumber;
	    return $this;
	}
	
	public function getPosition() {
	    return $this->position;
	}
	
	public function setPosition($position) {
	    $this->position = $position;
	    return $this;
	}
	
	public function getParticipation() {
	    return $this->participation;
	}
	
	public function setParticipation($participation) {
	    $this->participation = $participation;
	    return $this;
	}
	
}

?>