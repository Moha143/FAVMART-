<template>
<div class="container-fluid">
<div class="row justify-content-center">
  <div class="col-lg-10">
    <div  class="card shadow-lg border-4 rounded-lg mt-5">
 <div class="card-header">
<h3 class="text-center font-weight-light my-4">Add Fruits</h3>
 </div>
<div>
  <b-card class="card-body">
<b-form v-if="show">
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
        v-model="form.Image"
          placeholder="drop image"
    > </b-form-file>
  </b-form-group>
 </div>
  </b-row>
   <b-row class=" row mb-3">
   <div class="col-md-11">
      <b-button   class="btn btn-primary btn-block" name="add" @click="updatefruitss"  type="button" variant="primary">Update</b-button>
   </div>
   </b-row>
   <b-row class=" row mb-3">
   <div class="col-md-11">
      <b-button   class="btn btn-danger btn-block" name="reset" @click="deletefruit" type="button" variant="danger">Delete</b-button>
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
        id: this.$route.params.id,
        show: true
      }
    },

    methods: {
      getfruit(id) {
        MyServices.getfruitid(id).then(result => {
            console.log('details',result);
            this.form=result.data[0];
          
        })
      },
      updatefruitss(){
          this.form.id = this.id;
          MyServices.updateFruit(this.form).then(result=>{
            console.log("updated data", result);  

          });

      },
      deletefruit(){
       this.form.id = this.id;
        MyServices.deleteFruit(this.form).then(result=>{
          console.log("deleted data", result); 
        })
      }
    }, 
    created(){
        this.getfruit(this.$route.params.id);
    }
  }
</script>
