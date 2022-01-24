<template>
<div class="container-fluid">
<div class="row justify-content-center">
  <div class="col-lg-12">
    <div  class="card shadow-lg border-4 rounded-lg mt-5">
 <div class="card-header">
<h3 class="text-center font-weight-light my-4">Manage Fruits</h3>
 </div>
  <b-row style="" class=" row mb-3">
  </b-row>

  <!-- <b-row style="" class=" row mb-3">
   <div class="col-md-8">
    <b-form-group label-cols="2" label-cols-lg="5" label-size="lg"  label-for="input-6">
    <b-form-input id="input-6" size="xl"
        v-model="form.vsearch"
          type="text"
          placeholder="Search"
    > </b-form-input>
  </b-form-group>
 </div>
  </b-row> -->
   <div class="col-lg-12 grid-margin stretch-card">

 <b-table   bordered hover responsive :items="items" :fields="fields">
   <template #cell(#)="data">
     {{
       data.index+1
     }}

   </template>


 </b-table>
   </div>
</div>
</div>
</div>
</div>
</template>

<script lang="js">
import MyServices from '../../services/myservices'
export default {
  name: 'basicTables',
  data () {
    return {
      form: {
          vsearch: ''
        },
      items: [],
      fields: [ "#", {key: 'FName', label: 'Fruit Name'}, 'Price','Quantity','Unit',"TotalPrice",{key: 'Name', label: 'Customer Name'},{key: 'email', label: 'Customer email'}]
      }
  },
  methods: {

    getfruit(){
      MyServices.getorderss().then(result => {
      let docs =result.data;
      docs.forEach(element => {
            this.items.push(element.cart);
        });
      })
    },
    
  },
  created(){
    this.getfruit();
  }
}
</script>

<style scoped>

</style>
