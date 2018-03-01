var dtmLoc = location.hostname; 
var testinghosts = ["xxx.com"
                   ,"yyy.com"
                   ,"zzz.com"
                  ];



//stage Akamai, prod CMS
if((dtmLoc.indexOf(".xxx.") >= 0) && (testinghosts.indexOf(dtmLoc) < 0)){
  document.write('<script src="//www.53.com/dtm/e251f8161031ba53e6aefc36918d7e8f02c5e526/satelliteLib-0bf6a6472452184702edffb1fed34b2831ac0b7a.js"></script>');
  //console.log('PROD DTM');
} else {
  document.write('<script src="//assets.adobedtm.com/e251f8161031ba53e6aefc36918d7e8f02c5e526/satelliteLib-0bf6a6472452184702edffb1fed34b2831ac0b7a-staging.js"></script>');
  //console.log('STAGE Akamai DTM');
}
