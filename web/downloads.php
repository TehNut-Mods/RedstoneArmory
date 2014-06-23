<!DOCTYPE html>
<html>
	<title>Downloads - RArm | TehNut</title>	
	<head>
		<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
		<link rel="stylesheet" href="page.css">
        <link rel="shortcut icon" href="img/nutfavicon.png">
	</head>

	<body>
		<?php include 'incl/header.php'; ?>

		<div class="container">
			<div class="content">
				<?php
					include 'dl.php';
				
					$files = scandir('files/');
					natsort($files);
					$files = array_reverse($files);
					$versions = parse_ini_file('versions.ini');
					$first = true;
					$total_dls = 0;
					$downloads_str = '';
					$last_version = '';
					$old = false;
					$dl_location = ('files/');
					$mc_164 = '1.6.4';
					
					foreach($files as $file)
						if(strlen($file) > 2) {
							$dls_num = get_dls($file);
							$total_dls += intval($dls_num);
							$dls = number_format($dls_num);
							$button_type = 'old-dl';
							$cl_button_type = 'btn-sm';
														
							$file_version = substr($file, strlen('RArm-'));
							$version_name = substr($file_version, 0, strlen($file_version) - strlen('.jar'));
							$mc_version = $versions[$version_name];
							$version_name = str_replace('.', '-', $version_name);
							
							$dls_txt = 'Downloads';
							if($dls_num == 0)
								$dls_txt = 'Downloads :(';
							elseif($dls_num == 1)
								$dls_txt = 'Download';
							
							if($first) {
								$button_type = 'btn-lg';
								$cl_button_type = '';
							}
							
							if($last_version != $mc_version && !$first)
								$downloads_str .= "<br><font size='4'>Minecraft <b>$mc_version</b></font><br>";
							
							$downloads_str .= "<a href='$dl_location$file' class='btn $button_type btn-success dl'><b><span class='glyphicon glyphicon-download'></span> $file</b></a> <a href='changelog.php#$version_name' class='btn $cl_button_type btn-info'><b>Changelog</b></a> <a class='btn $cl_button_type btn-danger dl-counter'>Minecraft <b>$mc_164</b></a> <a href='$dl_location$file' class='btn $cl_button_type btn-warning dl-counter'><b>$dls</b> $dls_txt</a>";
							
							if($first) {
								$downloads_str .= '<br><br><hr><font size="6">Old Versions</font><br>';
								$first = false;
							} else {
								$downloads_str .= '<br>';
								$last_version = $mc_version;
							}
						}
					
					$total_dl_str = number_format($total_dls);
					$mod_launch = strtotime('2014-06-19 00:00:00');
					$current_time = time();
					$days_passed = ($current_time - $mod_launch) / 86400;
					$days_int = round($days_passed);
					$daily_dls = round($total_dls / $days_passed, 2);
					
					print("<div class='total-dls'><span class='glyphicon glyphicon-star'></span> Redstone Armory has been downloaded a total of <b>$total_dl_str</b> times over $days_int days. (About <i>$daily_dls</i> dls/day)</div>Redstone Armory is under a 'Don't be a Jerk' License, more info (for modpacks and other purposes) can be found in the footer. Also, the download counter is broken and I'm too lazy to fix it. :><br><br><br><font size='5'>Latest Download</font><br>");
					print($downloads_str);
				?>
			</div>
		</div>
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
		<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
		<script src="jquery_cookie.min.js"></script>
		<script> $(function() { $('#header-btn-downloads').addClass('active'); }); </script>
		<?php include 'incl/footer.php'; ?>	
	</body>
</html>