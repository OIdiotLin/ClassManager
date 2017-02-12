<?php
function show_index() {
echo "
<html>
<head>
	<title>ClassManager Server</title>
</head>

<body>
	<h1 style='text-align: center'>This is the server of app ClassManager</h1>
	<hr />

	<ul>
		<li>ip:</li>
		<li>time:</li>
	</ul>

	<p style='text-align: center'>
		Please contact with H.D. Lin.
	</p>

	<ul>
		<li>QQ: 791300089</li>
		<li>Email: <a href='mailto:lhd1997@qq.com'>lhd1997@qq.com</a></li>
		<li>Blog: <a href='http://oidiotlin.com/'>http://oidiotlin.com/</a></li>
	</ul>
</body>

</html>
";
}

if ($_SERVER['PHP_SELF'] == "/index.php")
	show_index();

?>