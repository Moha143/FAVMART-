<template>
<div class="container-fluid">
<div class="row justify-content-center">
  <div class="col-lg-12">
    <div  class="card shadow-lg border-4 rounded-lg mt-5">
 <div class="card-header">
<h3 class="text-center font-weight-light my-4">Add employee</h3>
 </div>
<div>
  <b-card class="card-body">
<b-form v-if="show">
  <b-row class=" row mb-3">
   <div class="col-md-11">
    <b-form-group label-cols="2" label-cols-lg="2" label-size="lg" label="Name" label-for="input-1">
    <b-form-input id="input-1" size="lg"
        v-model="form.EName"
          type="text"
          placeholder="Name"
    > </b-form-input>
  </b-form-group>
 </div>
  </b-row>
  <b-row class=" row mb-3">
   <div class="col-md-11">
    <b-form-group label-cols="2" label-cols-lg="2" label-size="lg" label="Email" label-for="input-2">
    <b-form-input id="input-2" size="lg"
        v-model="form.EEmail"
          type="text"
          placeholder="Enter Email"
    > </b-form-input>
  </b-form-group>
 </div>
  </b-row>
  <b-row class=" row mb-3">
   <div class="col-md-11">
    <b-form-group label-cols="2" label-cols-lg="2" label-size="lg" label="Phone" label-for="input-3">
    <b-form-input id="input-3" size="lg"
        v-model="form.ETell"
        type="number"
          placeholder=" Enter Phone number"
    > </b-form-input>
  </b-form-group>
 </div>
  </b-row>
   <b-row class=" row mb-3">
   <div class="col-md-11">
    <b-form-group label-cols="2" label-cols-lg="2" label-size="lg" label="Salary" label-for="input-4">
    <b-form-input id="input-4" size="lg"
        v-model="form.ESalary"
          type="number"
          placeholder="Enter Salary"
    > </b-form-input>
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
import MyServices from '../../services/myservices'

  export default {

      data() {
      return {
        form: {
          EName: '',
          EEmail: '',
          ETell: '',
          ESalary: '',
        
        },
        show: true
      }
    },

    methods: {
      SaveData() {
        MyServices.addNewEmp(this.form).then(result => {
          console.log(result);
        })
      },
      ResetData() {
        // Reset our form values

      }
      ,
    
    }
  }
</script>
