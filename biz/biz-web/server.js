const express = require('express');
const fs = require('fs');
const path = require('path');
const bodyParser = require('body-parser');
const app = express();

app.use(express.static('static'));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));
app.set('port', process.env.PORT || 3000);

app.get('/index.html',(req,res)=>{
    fs.readFile(path.join(__dirname,'index.html'))
});

app.listen(app.get('port'), function(err) {
  if(err) {
    console.log(err);
  }
  console.log('CORS-enabled web server listening on the localhost:'+ app.get('port'));
});
