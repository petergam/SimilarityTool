#!/usr/bin/perl -w

my @context = $ARGV[0];
my $jsonResponse = 0;
my $posTag = 0;

# Check parameters
if (@ARGV > 3 || @ARGV < 1 || $ARGV[0] eq "-h") {
	print "usage: disambiguate.pl string\neg: disambiguate.pl \"this is a string\"\n";
	print "use -j for JSON responses\"\n";
	print "use -t to force POS tagging\"\n";
	exit;
}
if (@ARGV == 1) {
	
} elsif (@ARGV == 2  && $ARGV[0] eq "-j") {
	$jsonResponse = 1;
	@context = $ARGV[1];
} elsif (@ARGV == 2  && $ARGV[0] eq "-t") {
	$posTag = 1;
	@context = $ARGV[1];
} elsif (@ARGV == 3  && $ARGV[0] eq "-j" && $ARGV[1] eq "-t") {
	$posTag = 1;
	$jsonResponse = 1;
	@context = $ARGV[2];
} else {
	print "Something went wrong";
	exit;
}

# Needed modules
use WordNet::SenseRelate::AllWords;
use WordNet::QueryData;
use WordNet::Tools;

# Create data query
my $qd = WordNet::QueryData->new;
my $queryDataError;
if ($jsonResponse) {
	$queryDataError = "{\"success\":0, \"message\":\"Construction of WordNet::QueryData failed\"}";
} else {
	$queryDataError = "Construction of WordNet::QueryData failed";
}

defined $qd or die $queryDataError;

# Create wordnet tool
my $wntools = WordNet::Tools->new($qd);
my $wnToolsError;
if ($jsonResponse) {
	$wnToolsError = "{\"success\":0, \"message\":\"Couldn't construct WordNet::Tools object\"}";
} else {
	$wnToolsError = "Couldn't construct WordNet::Tools object";
}

defined $wntools or die "\nCouldn't construct WordNet::Tools object";

# Create sense relate
my $wsd = WordNet::SenseRelate::AllWords->new (wordnet => $qd,wntools => $wntools,measure => 'WordNet::Similarity::lesk');

# Disambiguate text
my @results = $wsd->disambiguate (window => 3, tagged => $posTag,context => [@context], scheme => "sense1");

# If JSON response is expected
if($jsonResponse) {
	my $words = "";
	my $index = 0;
	@oldText = split(/ /, $context[0]);
 	foreach (@results) {
		@values = split(/#/, $_);
		$count =  scalar grep { defined $_ } @values;
		my $senseIndex;
		if ($count == 3) {
			$senseIndex = $values[2];
		} else {
			$senseIndex = 0;
		}
		
		$object = $oldText[$index] .  "\", \"newWord\":\"" . $values[0] . "\", \"tag\":\"" . $values[1] . "\", \"senseIndex\":" . $senseIndex;

		$words = $words . "\{\"word\":\"" . $object . "\},";
		$index = $index + 1;
 	}
	chop($words);
	my @response = "{\"success\": 1, \"relations\": [" . $words . "] }";
	print @response;
} else {
	print "@results";
}