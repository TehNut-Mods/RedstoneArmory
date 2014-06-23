<!DOCTYPE html>
<html>
	<title>RArm | TehNut</title>	
	<head>
		<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
		<link rel="stylesheet" href="page.css">
        <link rel="shortcut icon" href="img/nutfavicon.png">
	</head>

	<body>
		<?php include 'incl/header.php'; ?>
		
		<div class="container">
		<div class="content">
			<div id="myCarousel" class="carousel slide" data-ride="carousel">
			  <ol class="carousel-indicators">
				<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				<li data-target="#myCarousel" data-slide-to="1"></li>
				<li data-target="#myCarousel" data-slide-to="2"></li>
				<li data-target="#myCarousel" data-slide-to="3"></li>
			  </ol>
				  <div class="carousel-inner">
					<div class="item active">
					  <img src="img/ss0.png" alt="First slide">
					  <div class="container">
						<div class="carousel-caption">
						  <h1>Some Tools</h1>
						  <p>Enhance your Redstone Flux experience. Redstone Armory adds a total of<b>six</b> new Flux tools (currently). As a bonus, they each have their own special abilities to make your life easier.</p>
						  <p><a class="btn btn-lg btn-success" href="./downloads.php" role="button">Download Now</a></p>
						</div>
					  </div>
					</div>
					<div class="item">
					  <img src="img/ss1.png" alt="Second slide">
					  <div class="container">
						<div class="carousel-caption">
						  <h1>Built off a widely accepted power system (Plus more tools)</h1>
						  <p>To keep things simple, we build everything off of the <b>Redstone Flux</b> power system.</p>
						  <p><a class="btn btn-lg btn-success" href="./downloads.php" role="button">Download Now</a></p>
						</div>
					  </div>
					</div>
					<div class="item">
					  <img src="img/ss2.png" alt="Third slide">
					  <div class="container">
						<div class="carousel-caption">
						  <h1>Wait, these are the same tools...</h1>
						  <p>By utilizing <b>Redstone Flux</b> as our power system, we gain compatibility with countless (not really) mods!</p>
						  <p><a class="btn btn-lg btn-success" href="./downloads.php" role="button">Download Now</a></p>
						</div>
					  </div>
					</div>
					<div class="item">
					  <img src="img/ss3.png" alt="Fourth slide">
					  <div class="container">
						<div class="carousel-caption">
						  <h1>Kinda Thoroughly Documented</h1>
						  <p>The <b>FTBWiki</b> is the compedium of knowledge of this mod, easilly searchable in-browser via a search engine gives you all the information you'd ever need to know about Redstone Armory. Wait... isn't that the same picture?</p>
						  <p><a class="btn btn-lg btn-success" href="./downloads.php" role="button">Download Now</a></p>
						</div>
					  </div>
					</div>
				  </div>
				  <a class="left carousel-control" href="#myCarousel" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a>
				  <a class="right carousel-control" href="#myCarousel" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
				</div>
			</div>
			
		<div class="featurettes">
			<hr class="featurette-divider">

			<div class="row featurette">
				<div class="col-md-5">
					<!--<img class="featurette-image img-responsive" src="img/feature0.jpg" alt="Generic placeholder image">-->
				</div>
				<!--<div class="col-md-7">
					<h2 class="featurette-heading">Magic. <span class="text-muted">Naturally.</span></h2>
					<p class="lead">Botania is a magic tech mod based on nature. The main concept is to create magical flowers and devices utilizing the power of the earth, in the form of <i>Mana</i>.<br><br>
					
					The Mana system works through aiming Mana Spreaders into other blocks, because pipes or instantaneous connections are boring.<br><br>
					
					<b>Botania follows some design rules:</b><br>
					<span class="glyphicon glyphicon-ok"></span> No pipes, wires or equivalent, they are boring and overdone.<br>
					<span class="glyphicon glyphicon-ok"></span> Abstain from using GUIs in favour of in-world interaction.<br>
					<span class="glyphicon glyphicon-ok"></span> Refrain from showing numbers to the player. When you look at nature you don't see numbers.<br>
					<span class="glyphicon glyphicon-ok"></span> Provide pleasing visuals utilizing only two different particle effects.<br>
					<span class="glyphicon glyphicon-ok"></span> Utilize renewable resources where possible.<br><br>
					<a class="btn btn-lg btn-success" href="./downloads.php" role="button">Download Redstone Armory</a>
					</p>
				</div>-->
			</div>

			<hr class="featurette-divider">

			<div class="row featurette">
				<div class="col-md-7">
					<h2 class="featurette-heading">Not convinced yet? <span class="text-muted">Check out some videos.</span></h2>
					<div class="video-intro">Spotlights that show off the main features.</div>
					<div class="youtube"><p>This is where videos would go... <b>If I had any!</b></div>
					</div>
				</div>
			</div>
		</div>	
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
		<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
		<script src="jquery_cookie.min.js"></script>
		<script> $(function() { $('#header-btn-home').addClass('active'); }); </script>
		<?php include 'incl/footer.php'; ?>
	</body>
</html>