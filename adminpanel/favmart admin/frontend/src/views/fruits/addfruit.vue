<template>
<div class="container-fluid">
<div class="row justify-content-center">
  <div class="col-lg-12">
    <div  class="card shadow-lg border-4 rounded-lg mt-5">
 <div class="card-header">
<h3 class="text-center font-weight-light my-4">Add Fruits</h3>
 </div>
<div>
  <b-card class="card-body">
<b-form v-if="show" enctype="multipart/form-data">
  <b-row class=" row mb-3">
   <div class="col-md-11">
    <b-form-group label-cols="2" label-cols-lg="2" label-size="lg" label="Name" label-for="input-1">
    <b-form-input id="input-1" size="lg"
        v-model="form.FName"
          type="text"
          placeholder="Enter Fruit Name"
    > </b-form-input>
  </b-form-group>
 </div>
  </b-row>
  <b-row class=" row mb-3">
   <div class="col-md-11">
    <b-form-group label-cols="2" label-cols-lg="2" label-size="lg" label="Unit" label-for="input-2">
    <b-form-input id="input-2" size="lg"
        v-model="form.Unit"
          type="text"
          value="Package"
          disabled
          placeholder="package"
    > </b-form-input>
  </b-form-group>
 </div>
  </b-row>
  <b-row class=" row mb-3">
   <div class="col-md-11">
    <b-form-group label-cols="2" label-cols-lg="2" label-size="lg" label="Quantity" label-for="input-3">
    <b-form-input id="input-3" size="lg"
        v-model="form.Quantity"
          type="number"
          placeholder="Enter Fruit Quantity"
    > </b-form-input>
  </b-form-group>
 </div>
  </b-row>
   <b-row class=" row mb-3">
   <div class="col-md-11">
    <b-form-group label-cols="2" label-cols-lg="2" label-size="lg" label="Price" label-for="input-4">
    <b-form-input id="input-4" size="lg"
        v-model="form.Price"
          type="number"
          placeholder="Enter Fruit Price"
    > </b-form-input>
  </b-form-group>
 </div>
  </b-row>
   <b-row class=" row mb-3">
   <div class="col-md-11">
    <b-form-group label-cols="2" label-cols-lg="2" label-size="lg" label="Description" label-for="input-5">
    <b-form-textarea id="input-5" size="lg"
        v-model="form.Description"
          type="text"
          placeholder="Enter Fruit Description"
    > </b-form-textarea>
  </b-form-group>
 </div>
  </b-row>
   
    <b-row class=" row mb-3">
   <div class="col-md-11">
    <b-form-group label-cols="2" label-cols-lg="2" label-size="lg" label="Image" label-for="input-7">
    <b-form-file id="input-7" size="lg"
     @change="uploadImage"
        v-model="form.Image"
          placeholder="drop image"
    > </b-form-file>
  </b-form-group>
 </div>
  </b-row>
   <b-row class=" row mb-3">
   <div class="col-md-11">
      <b-button   class="btn btn-primary btn-block" name="add" @click="SaveData"  type="button" variant="primary">Save</b-button>
   </div>
   </b-row>
   <b-row class=" row mb-3">
   <div class="col-md-11">
      <b-button   class="btn btn-danger btn-block" name="reset" @click="ResetData" type="button" variant="danger">Reset</b-button>
   </div>
   </b-row>
    </b-form>
  </b-card>
</div>
</div>
</div>
</div>
</div>

</template>
<script>
import myservices from '../../services/myservices'
import MyServices from '../../services/myservices'

  export default {

      data() {
      return {
        form: {
          FName: '',
          Unit: 'package',
          Price: '',
          Quantity: '',
          Description: '',
          Image: '',
        
        },
        show: true
      }
    },

    methods: {
      SaveData() {
        MyServices.addNewFruit(this.form).then(result => {
          console.log(result);
        })
      },
      ResetData() {

         MyServices.uploadimage(this.form).then(result => {
          console.log(result);
        })
        // Reset our form values

        // this.form.vname = ''
        // this.form.vunit = ''
        // this.form.vprice = ''
        // this.form.vquantity = ''
        // this.form.dest = ''
        // this.form.date = ''
        // this.form.vimg = ''
        // Trick to reset/clear native browser form validation state
        // this.show = false
        // this.$nextTick(() => {
        //   this.show = true
        // })
      }
      ,
       uploadImage(e){

      if(e.target.files[0]){
        
          let file = e.target.files[0];
          const config = { headers: { 'Content-Type': 'multipart/form-data' } }
          const data = new FormData();
          // var imagefile = document.querySelector('#avatar')
          console.log('image = ' + file);
          data.append('Image', file)
          // data.append('user', this.profile.id)
          // axios.post('/app/avatar/', data, config)
          myservices.uploadimage(JSON.stringify(data))
          .then(response => {
            console.log(response, '<<< response.data >>>')
          })
          .catch(error => {
            console.log(error)
          })
              // const formData = new FormData();
          // formData.append('file',file);
          //   MyServices.uploadimage(file.toString()).then(result => {
          // console.log(result);
          // });

    
          // var storageRef = fb.storage().ref('products/'+ Math.random() + '_'  + file.name);
    
          // let uploadTask  = storageRef.put(file);
    
          // uploadTask.on('state_changed', (snapshot) => {
            
          // }, (error) => {
          //   // Handle unsuccessful uploads
          // }, () => {
          //   // Handle successful uploads on complete
          //   // For instance, get the download URL: https://firebasestorage.googleapis.com/...
            
          //   uploadTask.snapshot.ref.getDownloadURL().then((downloadURL) => {
          //     this.product.images.push(downloadURL);
          //   });

          // });

      }
    },
    }
  }
</script>
