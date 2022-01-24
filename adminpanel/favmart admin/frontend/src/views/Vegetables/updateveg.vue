<template>
<div class="container-fluid">
<div class="row justify-content-center">
  <div class="col-lg-10">
    <div  class="card shadow-lg border-4 rounded-lg mt-5">
 <div class="card-header">
<h3 class="text-center font-weight-light my-4">update Vegetables</h3>
 </div>
<div>
  <b-card class="card-body">
<b-form v-if="show">
  <b-row class=" row mb-3">
   <div class="col-md-11">
    <b-form-group label-cols="2" label-cols-lg="2" label-size="lg" label="Name" label-for="input-1">
    <b-form-input id="input-1" size="lg"
        v-model="form.VName"
          type="text"
          placeholder="Enter Vegetable Name"
    > </b-form-input>
  </b-form-group>
 </div>
  </b-row>
  <b-row class=" row mb-3">
   <div class="col-md-11">
    <b-form-group label-cols="2" label-cols-lg="2" label-size="lg" label="Unit" label-for="input-2">
    <b-form-input id="input-2" size="lg"
        v-model="form.VUnit"
          type="text"
          value="Kilo"
          disabled
          placeholder="Kilo"
    > </b-form-input>
  </b-form-group>
 </div>
  </b-row>
  <b-row class=" row mb-3">
   <div class="col-md-11">
    <b-form-group label-cols="2" label-cols-lg="2" label-size="lg" label="Quantity" label-for="input-3">
    <b-form-input id="input-3" size="lg"
        v-model="form.VQuantity"
          type="number"
          placeholder="Enter Vegetabe Quantity"
    > </b-form-input>
  </b-form-group>
 </div>
  </b-row>
   <b-row class=" row mb-3">
   <div class="col-md-11">
    <b-form-group label-cols="2" label-cols-lg="2" label-size="lg" label="Price" label-for="input-4">
    <b-form-input id="input-4" size="lg"
        v-model="form.VPrice"
          type="number"
          placeholder="Enter Vegetable Price"
    > </b-form-input>
  </b-form-group>
 </div>
  </b-row>
   <b-row class=" row mb-3">
   <div class="col-md-11">
    <b-form-group label-cols="2" label-cols-lg="2" label-size="lg" label="Description" label-for="input-5">
    <b-form-textarea id="input-5" size="lg"
        v-model="form.VDescription"
          type="text"
          placeholder="Enter Vegetable Description"
    > </b-form-textarea>
  </b-form-group>
 </div>
  </b-row>
   
    <b-row class=" row mb-3">
   <div class="col-md-11">
    <b-form-group label-cols="2" label-cols-lg="2" label-size="lg" label="Image" label-for="input-7">
    <b-form-file id="input-7" size="lg"
        v-model="form.VImage"
          placeholder="drop image"
    > </b-form-file>
  </b-form-group>
 </div>
  </b-row>
   <b-row class=" row mb-3">
   <div class="col-md-11">
      <b-button   class="btn btn-primary btn-block" name="add" @click="updateVegetabless"  type="button" variant="primary">Save</b-button>
   </div>
   </b-row>
    <b-row class=" row mb-3">
   <div class="col-md-11">
      <b-button   class="btn btn-danger btn-block" name="reset" @click="deleteveg" type="button" variant="danger">Delete</b-button>
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
          VName: '',
          VUnit: 'Kilo',
          VPrice: '',
          VQuantity: '',
          VDescription: '',
          VImage: '',
        
        },
         id: this.$route.params.id,
        show: true
      }
    },
    methods: {
    
    getveg(id) {
        MyServices.getvegetableid(id).then(result => {
            console.log('details',result);
            this.form=result.data[0];
          
        })
      },
      updateVegetabless(){
          this.form.id = this.id;
          MyServices.updateVegetable(this.form).then(result=>{
            console.log("updated data", result);  
          });
      },
      deleteveg(){
       this.form.id = this.id;
        MyServices.deleteVegetable(this.form).then(result=>{
          console.log("deleted data", result); 
        })
      }
      
      
    },
    
    created(){
        this.getveg(this.$route.params.id);
    }
    
  }
</script>
