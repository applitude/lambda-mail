// load aws sdk
var aws = require('aws-sdk');

var ses = new aws.SES();

const from = "no-reply@ses.applitude.no";

exports.handler = function (event, context, callback) {

    context.callbackWaitsForEmptyEventLoop = false;

    var to = event.to;
    var body = event.body;
    var title = event.title;

    ses.sendEmail( {
        Source: from,
        Destination: { ToAddresses: [to] },
        Message: {
            Subject:{
                Data: title
            },
            Body: {
                Text: {
                    Data: body
                }
            }
        }
            
    }
    , function (err, data) {
        if(err) {
            callback(err, 'Sending mail failed!');
        }else{
            callback(null, 'Mail successfully sendt!');
        }
    });
};