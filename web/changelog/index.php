<head>
  <meta charset="utf-8">
  <title>Changelog</title>
  <link rel="shortcut icon" href="../img/nutfavicon.png">
  <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic|Lato:300,400,500,600' rel='stylesheet' type='text/css' />
  <link href='http://code.ionicframework.com/ionicons/1.5.2/css/ionicons.min.css' rel='stylesheet' type='text/css' />
  <link href='../css/changelog.css?' rel='stylesheet' type='text/css' />
</head>
<body>
<?php include '../include/header.php'; ?>

<div class="container">
	<div class="box">
    	<div class="inner">
    		<h1>
        		<span class="icon ion-ios7-paper"></span>
    			Redstone Armory Changelog
        	</h1>
            <p>Version formatting: <b>MCVER-MAIN.SUB-BUILD</b></p>
    	</div>
    </div>
  	<?php
		$versions = array();
		$file = file('changelog.txt');
		$changelog = '';
		$first = true;
		
		foreach($file as $line) {
			if(strpos($line, '*') !== 0) {
				$key = trim(str_replace('.', '-', str_replace(' ', '-', $line)));
				$version = ucfirst(trim($line));
				
				array_push($versions, $key);
				if(!$first)
					$changelog .= '';
				$changelog .= "<hr><div id='$key-fake'><h3>$version</h3></div>";
				$first = false;
			} else {
				$change = substr($line, 1);
				$changelog .= "<li>$change</li>";
			}
		}
					
		echo($changelog);
	?>
</div>

<?php include '../include/footer.php'; ?>
</body>
</html>
