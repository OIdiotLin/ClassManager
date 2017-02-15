<?php

class Response {
	/**
	 * 以 json 格式封装通信方法并输出
	 * @param	integer	$code	状态码
	 * @param	string	$message	消息提示
	 * @param	array	$data	数据
	 */
	public static function json($code, $message = '', $data = array()) {
		if (!is_numeric($code)) {
			return '';
		}
		$result = array (
			'code' => $code,
			'message' => $message,
			'data' => $data
		);
		echo json_encode($result);
	}

	/**
	 * 以 json 格式封装通信方法并输出
	 * @param	integer	$code	状态码
	 * @param	string	$message	消息提示
	 * @param	array	$data	数据
	 */
	public static function xml($code, $message = '', $data = array()) {
		if (!is_numeric($code)) {
			return '';
		}
		$result = array (
			'code' => $code,
			'message' => $message,
			'data' => $data
		);
	
		header("Content-Type:text/xml; charset=UTF-8");	// 指定页面显示类型
		$xml = "<?xml version='1.0' encoding='UTF-8'?>\n";
		$xml .= "<root>\n";
		$xml .= self::xmlEncode($result);
		$xml .= "</root>\n";
		echo $xml;
	}

	private static function xmlEncode($data) {
		$xml = $attr = "";
		foreach ($data as $key => $value) {
			if (is_numeric($key)) {		// 如果 $key 是个数字
				$attr = " id='{$key}'";	// 则建立 item 子节点并将 $key 设为 id
				$key = "item";
			}
			$xml .= "<{$key}";
			$xml .= "{$attr}>";
			$xml .= is_array($value) ? self::xmlEncode($value) : $value;	// 递归展开子节点
			$xml .= "</{$key}>\n";
		}
		return $xml;
	}

	/**
	 * 以 json/xml 格式封装通信方法并输出
	 * @param	integer	$code	状态码
	 * @param	string	$message	消息提示
	 * @param	array	$data	数据
	 */
	public static function show($code, $message = '', $data = array()) {
		$type = isset($_GET['format']) ? $_GET['format'] : 'xml';
		
		$result = array(
			'code' => $code,
			'message' => $message,
			'data' => $data
		);
		
		if ($type == 'json') {
			self::json($code, $message, $data);
		} elseif ($type == 'xml') {
			self::xml($code, $message, $data);
		} elseif ($type == 'array') {	// ONLY WHEN DEBUGGING!
			var_dump($result);			// THIS WILL TRANSFER NOTHING TO CLIENT
		} else {
			// TODO something else
		}
	}
}

?>