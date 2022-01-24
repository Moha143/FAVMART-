<template>
    <div class="col-12 grid-margin">
        <div class="card">
            <div class="card-body">
                <div class="card-body">
                    <h4 class="card-title">U Dir Fariin macmiilka</h4>
                    <form class="form-sample">
                        <div class="row">
                            <div class="col-md-6">
                            <b-form-group horizontal label="Cinwaanka" description="Cinwaanka" label-for="cinwan">
                                <b-form-input type="text" id="cinwan" v-model="formData.cinwan"></b-form-input>
                            </b-form-group>
                            </div>
                            <div class="col-md-6">
                            <b-form-group horizontal label="Fariintta" description="Fariin" label-for="fariin">
                                <b-form-input type="text" id="fariin" v-model="formData.fariin"></b-form-input>
                            </b-form-group>
                            </div>
                        </div>
                        <div align="center">
                            <b-button  variant="success" class="mr-2" @click="sendPush">Send</b-button>
                            <!-- <b-button  variant="success" class="mr-2" @click="subscribe">Subscribe</b-button> -->
                            <!-- <b-button  variant="success" class="mr-2" @click="subscribeToDrivers">SubscribeToDrivers</b-button> -->
                        </div>
                    </form>
                </div>
            </div>

        </div>
    </div>
</template>

<script>
import MyServices from '../../services/myservices';
export default {
    data() {
        return {
            formData: {
                cinwan: '',
                fariin: '',
               
            }
        }
    },
    methods: {
       sendPush(){
           MyServices.sendPushToMultiUser(this.formData)
           .then(result => {
               console.log('result',result);
           })
           .catch(err => {
               console.log('error ', err);
           })
       },
    }
}
</script>
<style scoped>

</style>