const functions = require("firebase-functions");
// // Create and Deploy Your First Cloud Functions
// // https://firebase.google.com/docs/functions/write-firebase-functions
//
// exports.helloWorld = functions.https.onRequest((request, response) => {
//   functions.logger.info("Hello logs!", {structuredData: true});
//   response.send("Hello from Firebase!");
// });
const admin = require('firebase-admin');
const firebaseHelper =require('firebase-functions-helper');
const  express =require('express');
const cors = require('cors');
const Multer = require('multer')
const bodyParser= require("body-parser");
var fetch = require("fetch");
// admin.initializeApp(functions.config().firebase);
//const fb = firebase.initializeApp(config);

var serviceAccount = require("./favmart.json");

admin.initializeApp({
  credential: admin.credential.cert(serviceAccount),
  databaseURL: "https://favmart-company-project.firebaseio.com",
  storageBucket: "favmart-company-project.appspot.com"
});
const db = admin.firestore();
const app = express();
app.use(cors({ origin: true }));
const main = express();
// var commonUrl = "http://localhost:5001/favmart-company-project/us-central1/app/api";
var commonUrl = "https://us-central1-favmart-company-project.cloudfunctions.net/app/api/";
const contactsCollection = 'vegetables';
// var serviceAccount = require("./favmart.json");
const bucket = admin.storage().bucket();
const multer = Multer({
    storage: Multer.memoryStorage(),
    limits: {
      fileSize: 5 * 1024 * 1024 // no larger than 5mb, you can change as needed.
    }
  });
// main.use('/api/v1', app);
// main.use(bodyParser.json());
// main.use(bodyParser.urlencoded({ extended: false }));
// webApi is your functions name, and you will pass main as 
// a parameter
// exports.webApi = functions.https.onRequest(main);

// // View all contacts
// app.get('/vegetables', (req, res) => {
//     firebaseHelper.firestore
//         .backup(db, contactsCollection)
//         .then(data => res.status(200).send(data))
//         .catch(error => res.status(400).send(`Cannot get contacts: ${error}`));
// })
// app.use(function(req, res, next) {
//     var url = "http://localhost:5001/favmart-company-project/us-central1/app/api";
//     // url = "https://us-central1-neat-bank-208917.cloudfunctions.net/app/"
//     res.header("Access-Control-Allow-Origin", url); // update to match the domain you will make the request from
//     res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
//     next();
// })
//https://www.youtube.com/watch?v=wuShuZYtJ0w

/**
 * Upload the image file to Google Storage
 * @param {File} file object that will be uploaded to Google Storage
 */
 const uploadImageToStorage = (file) => {
    return new Promise((resolve, reject) => {
      if (!file) {
        reject('No image file');
      }
      let newFileName = `${file.originalname}_${Date.now()}`;
  
      let fileUpload = bucket.file(newFileName);
  
      const blobStream = fileUpload.createWriteStream({
        metadata: {
          contentType: file.mimetype
        }
      });
  
      blobStream.on('error', (error) => {
        reject('Myerror. ' + error);
      });
  
      blobStream.on('finish', () => {
        // The public URL can be used to directly access the file via HTTP.
        const url = format(`https://storage.googleapis.com/${bucket.name}/${fileUpload.name}`);
        resolve(url);
      });
  
      blobStream.end(file.buffer);
    });
  }

  app.post('/api/upload', (req, res) => {
    console.log('Upload Image', req.body);
  
    let file = req.body.Image;
    if (file) {
      uploadImageToStorage(file).then((success) => {
        res.status(200).send({
          status: 'success'
        });
      }).catch((error) => {
        console.error(error);
      });
    }
  });


//   app.post('/api/upload', multer.single('file'), (req, res) => {
//     console.log('Upload Image');
  
//     let file = req.file;
//     if (file) {
//       uploadImageToStorage(file).then((success) => {
//         res.status(200).send({
//           status: 'success'
//         });
//       }).catch((error) => {
//         console.error(error);
//       });
//     }
//   });

app.get('/api/vegtables', (req, res) => {
    (async () => {
        try {
            console.log("request body",req.body);
            let query = db.collection('vegetables');
            
            let response = [];
            await query.get().then(querySnapshot => {
            let docs = querySnapshot.docs;
            for (let doc of docs) {
                const vegetables = {
                    vegetable: doc.data()
                };
                vegetables.vegetable.id = doc.id;
                response.push(vegetables);
            }
            });
            return res.status(200).send(response);
        } catch (error) {
            console.log(error);
            return res.status(500).send(error);
        }
        })();
    });

app.get('/api/fruits', (req, res) => {
    (async () => {
        try {
            console.log("request body",req.body);
            let query = db.collection('fruits');
            let response = [];
            await query.get().then(querySnapshot => {
            let docs = querySnapshot.docs;
            for (let doc of docs) {
                const fruits = {
                    fruit: doc.data()
                };
                fruits.fruit.id = doc.id;
                response.push(fruits);
            }
            });
            return res.status(200).send(response);
        } catch (error) {
            console.log(error);
            return res.status(500).send(error);
        }
        })();
    });
    app.get('/api/uusers', (req, res) => {
    (async () => {
        try {
            console.log("request body",req.body);
            let query = db.collection('users');
            let response = [];
            await query.get().then(querySnapshot => {
            let docs = querySnapshot.docs;
            for (let doc of docs) {
                const users = {
                    user: doc.data()
                };
                users.user.id = doc.id;
                response.push(users);
            }
            });
            return res.status(200).send(response);
        } catch (error) {
            console.log(error);
            return res.status(500).send(error);
        }
        })();
    });
    app.get('/api/oooooooo', (req, res) => {
        (async () => {
            try {
                console.log("request body",req.body);
                let query = db.collection('mycart');
                let response = [];
                await query.get().then(querySnapshot => {
                let docs = querySnapshot.docs;
                for (let doc of docs) {
                    const carts = {
                        cart: doc.data()
                    };
                    carts.cart.id = doc.id;
                    response.push(carts);
                }
                });
                return res.status(200).send(response);
                return res.status(200).send(response);
            } catch (error) {
                console.log(error);
                return res.status(500).send(error);
            }
            })();
        });
app.get('/api/feedback', (req, res) => {
    (async () => {
        try {
            console.log("request body",req.body);
            let query = db.collection('Feedback');
            let response = [];
            await query.get().then(querySnapshot => {
            let docs = querySnapshot.docs;
            for (let doc of docs) {
                const feedbacks = {
                    feedback: doc.data()
                };
                feedbacks.feedback.id = doc.id;
                response.push(feedbacks);
            }
            });
            return res.status(200).send(response);
        } catch (error) {
            console.log(error);
            return res.status(500).send(error);
        }
        })();
    });

    
    // fruits module
app.post('/api/newfruit', (req, res) => {
    (async () => {
        try {
        //   await db.collection('items').doc('/' + req.body.id + '/')
        //       .create({item: req.body.item});
        console.log("request body",req.body);
        // if (!req.body.Price) {
        //     res.status(400).send({
        //         message: "Content can not be empty!"
        //     });
        //     return;
        // }
            const fruit = req.body;
            fruit.Date = admin.firestore.Timestamp.fromDate(new Date());
            await db.collection('fruits').add(fruit).then(respo => {
            // return res.status(200).send(response);
            return res.status(200).send("success");
            // response = respo;
            }).catch(err => {
                return res.status(500).send(err);
            })

        } catch (error) {
            console.log(error);
            return res.status(500).send(error);
        }
        })();
    });
app.get('/api/getfruitID/:id', (req, res) => {
    (async () => {
        try {
            console.log("request body",req.body);
            console.log('parms ', req.params);
            let response = [];
            let document = db.collection('fruits').doc(req.params.id);
            await document.get().then(result=>{

                response.push(result.data());
            });
            return res.status(200).send(response);
        } catch (error) {
            console.log(error);
            return res.status(500).send(error);
        }
        })();
    });
app.post('/api/updatefruit', (req, res) => {
(async () => {
    try {
        console.log("request body",req.body);
        if (!req.body.id) {
            res.status(400).send({
                message: "Content can not be empty!"
            });
            return;
        }
        const fruit = req.body;
        console.log('Fruit ID', fruit.id);
        db.collection('fruits').doc(fruit.id).update(fruit,{
            merge: true
        })
        .then(result => {
            console.log("updated Fruits");
            return res.status(200).send("updated");
        }).catch(error => {
            return res.status(500).send(error);
        })
    } catch (error) {
        console.log(error);
        return res.status(500).send(error);
    }
    })();
});

app.post('/api/deletefruit', (req, res) => {
    (async () => {
        try {
            // console.log("request body",req.body);
            if (!req.body.id) {
                res.status(400).send({
                    message: "Content can not be empty!"
                });
                return;
            }
            const fruit = req.body;
            console.log('fruit data = ', fruit);
            await db.collection('fruits').doc(fruit.id).delete()
            .then(dbResult => {
                console.log("deleted fruit from db");
                return res.status(200).send("deleted");
            })
            .catch(error => {
                console.log('error deleting user in db',error);
                return res.status(500).send(error);
            })
        } catch (error) {
            console.log(error);
            return res.status(500).send(error);
        }
        })();
    });


    // Vegetable module
    app.get('/api/getvegetableID/:id', (req, res) => {
        (async () => {
            try {
                console.log("request body",req.body);
                console.log('parms ', req.params);
                let response = [];
                let document = db.collection('vegetables').doc(req.params.id);
                await document.get().then(result=>{
    
                    response.push(result.data());
                });
                return res.status(200).send(response);
            } catch (error) {
                console.log(error);
                return res.status(500).send(error);
            }
            })();
        });
app.post('/api/newvegetable', (req, res) => {
    (async () => {
        try {
        //   await db.collection('items').doc('/' + req.body.id + '/')
        //       .create({item: req.body.item});
        console.log("request body",req.body);
        // if (!req.body.Price) {
        //     res.status(400).send({
        //         message: "Content can not be empty!"
        //     });
        //     return;
        // }
            const vegetable = req.body;
            vegetable.VDate = admin.firestore.Timestamp.fromDate(new Date());
            await db.collection('vegetables').add(vegetable).then(respo => {
            // return res.status(200).send(response);
            return res.status(200).send("success");
            // response = respo;
            }).catch(err => {
                return res.status(500).send(err);
            })

        } catch (error) {
            console.log(error);
            return res.status(500).send(error);
        }
        })();
    });

app.post('/api/updatevegetable', (req, res) => {
(async () => {
    try {
        console.log("request body",req.body);
        if (!req.body.id) {
            res.status(400).send({
                message: "Content can not be empty!"
            });
            return;
        }
        const vegetable = req.body;
        console.log('Vegetable ID', vegetable.id);
        db.collection('vegetables').doc(vegetable.id).update(vegetable,{
            merge: true
        })
        .then(result => {
            console.log("updated vegetable");
            return res.status(200).send("updated");
        }).catch(error => {
            return res.status(500).send(error);
        })
    } catch (error) {
        console.log(error);
        return res.status(500).send(error);
    }
    })();
});

app.post('/api/deletevegetable', (req, res) => {
    (async () => {
        try {
            // console.log("request body",req.body);
            if (!req.body.id) {
                res.status(400).send({
                    message: "Content can not be empty!"
                });
                return;
            }
            const vegetable = req.body;
            console.log('driver data = ', vegetable);
            await db.collection('vegetables').doc(vegetable.id).delete()
            .then(dbResult => {
                console.log("deleted vegetable from db");
                return res.status(200).send("deleted");
            })
            .catch(error => {
                console.log('error deleting user in db',error);
                return res.status(500).send(error);
            })
        } catch (error) {
            console.log(error);
            return res.status(500).send(error);
        }
        })();
    });

        // fruits module
app.post('/api/newemp', (req, res) => {
    (async () => {
        try {
        //   await db.collection('items').doc('/' + req.body.id + '/')
        //       .create({item: req.body.item});
        console.log("request body",req.body);
        // if (!req.body.Price) {
        //     res.status(400).send({
        //         message: "Content can not be empty!"
        //     });
        //     return;
        // }
            const emp = req.body;
            emp.Date = admin.firestore.Timestamp.fromDate(new Date());
            await db.collection('employees').add(emp).then(respo => {
            // return res.status(200).send(response);
            return res.status(200).send("success");
            // response = respo;
            }).catch(err => {
                return res.status(500).send(err);
            })

        } catch (error) {
            console.log(error);
            return res.status(500).send(error);
        }
        })();
    });
app.get('/api/getempID/:id', (req, res) => {
    (async () => {
        try {
            console.log("request body",req.body);
            console.log('parms ', req.params);
            let response = [];
            let document = db.collection('employees').doc(req.params.id);
            await document.get().then(result=>{

                response.push(result.data());
            });
            return res.status(200).send(response);
        } catch (error) {
            console.log(error);
            return res.status(500).send(error);
        }
        })();
    });
app.post('/api/uEmp', (req, res) => {
(async () => {
    try {
        console.log("request body",req.body);
        if (!req.body.id) {
            res.status(400).send({
                message: "Content can not be empty!"
            });
            return;
        }
        const fruit = req.body;
        console.log('Fruit ID', fruit.id);
        db.collection('employees').doc(fruit.id).update(fruit,{
            merge: true
        })
        .then(result => {
            console.log("updated Fruits");
            return res.status(200).send("updated");
        }).catch(error => {
            return res.status(500).send(error);
        })
    } catch (error) {
        console.log(error);
        return res.status(500).send(error);
    }
    })();
});

app.post('/api/dEmp', (req, res) => {
    (async () => {
        try {
            // console.log("request body",req.body);
            if (!req.body.id) {
                res.status(400).send({
                    message: "Content can not be empty!"
                });
                return;
            }
            const fruit = req.body;
            console.log('fruit data = ', fruit);
            await db.collection('employees').doc(fruit.id).delete()
            .then(dbResult => {
                console.log("deleted fruit from db");
                return res.status(200).send("deleted");
            })
            .catch(error => {
                console.log('error deleting user in db',error);
                return res.status(500).send(error);
            })
        } catch (error) {
            console.log(error);
            return res.status(500).send(error);
        }
        })();
    });
    app.get('/api/emp', (req, res) => {
        (async () => {
            try {
                console.log("request body",req.body);
                let query = db.collection('employees');
                let response = [];
                await query.get().then(querySnapshot => {
                let docs = querySnapshot.docs;
                for (let doc of docs) {
                    const employees = {
                        employee: doc.data()
                    };
                    employees.employee.id = doc.id;
                    response.push(employees);
                }
                });
                return res.status(200).send(response);
            } catch (error) {
                console.log(error);
                return res.status(500).send(error);
            }
            })();
        });
    //Notification


    app.post('/api/sendSinglePush', (req,res) => {
        ( async () => {
            try {
                console.log("request body",req.body);
                if (!req.body.phone) {
                    res.status(400).send({
                        message: "Content can not be empty!"
                    });
                    return;
                }
                var token;
                // var message = req.body.fariin;
                var message = {
                    notification: {
                        title: req.body.cinwan,
                        body: req.body.fariin
                    }
                };
                await db.collection('users').where('phone','==',req.body.phone).get()
                .then(result => {
                    let docs = result.docs;
                    for(let doc of docs){
                        token = doc.data().token;
                    }
                    admin.messaging().sendToDevice(token,message)
                    .then(result => {
                        return res.status(200).send("success");
                    })
                }).catch(error => {
                    return res.status(500).send(error);
                })
                // location = new admin.firestore.GeoPoint(0,0);
            }catch (error){
                return res.status(500).send(error);
            }
        })();
    })
    
    app.post('/api/sendMultiPush', (req, res) => {
        ( async () => {
            try{
                console.log('sending mutiple push to riders');
                console.log("request body",req.body);
                // var token;
                // var message = req.body.fariin;
                var message = {
                    notification: {
                        title: req.body.cinwan,
                        body: req.body.fariin
                    },
                    topic: 'users'
                };
                // Send a message to devices subscribed to the provided topic.
                admin.messaging().send(message)
                .then((response) => {
                // Response is a message ID string.
                    console.log('Successfully sent message:', response);
                    return res.status(200).send("success");
                })
                .catch((error) => {
                    console.log('Error sending message:', error);
                    return res.status(500).send(error);
                });
    
    
            }catch(error){
                return res.status(500).send(error);
            }
        })();
    })

    exports.newProduct = functions.firestore.document('vegetables/{autoid}')
    .onCreate( async (snapshot, contex) => {
        const newData = snapshot.data();
        const vetgableName = newData.VName;

        const pushObject = {
            cinwan: 'New Vegtable Added',
            fariin: 'New Vetgtable added ' + vetgableName
        }
        var jsonPush = JSON.stringify(pushObject);
        await fetch(commonUrl + "/sendMultiPush", {
            method: 'post',
            body: jsonPush,
            headers: { 'Content-Type': 'application/json' },
        });
    });
    exports.app = functions.https.onRequest(app);