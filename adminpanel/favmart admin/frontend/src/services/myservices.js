import http from '../http-common';
class MyServices{
    //Fruits
    addNewFruit(data){
        return http.post("/newfruit",data);
    }
    updateFruit(data){
       return  http.post("/updatefruit",data);
    }
    deleteFruit(data){
       return http.post("/deletefruit",data);
    }
    getfruits(){
        return http.get("/fruits");
    }
    getfruitid(id)
    {
        return  http.get(`/getfruitID/${id}`);

    }
  
    uploadimage(data){
        return  http.post("/upload",data);
        // {
        //     headers: {
        //         'Content-Type': 'multipart/form-data'
        //       }
        // };
     }

    // Vegetables
    addNewVegetable(data){
        return http.post("/newvegetable",data);
    }
    updateVegetable(data){
       return  http.post("/updatevegetable",data);
    }
    deleteVegetable(data){
       return http.post("/deletevegetable",data);
    }
    getVegetables(){
        return http.get("/vegtables");
    }
    getvegetableid(id)
    {
        return  http.get(`/getvegetableID/${id}`);

    }
    // get customer feedback
    getfeedback(){
        return http.get("/feedback");
    }

     // get user info
     getusers(){
        return http.get("/uusers");
    }
    getorderss(){
        return http.get("/oooooooo");
    }

    //Employee model
    addNewEmp(data){
        return http.post("/newemp",data);
    }
    updateEmp(data){
       return  http.post("/uEmp",data);
    }
    deleteEmp(data){
       return http.post("/dEmp",data);
    }
    getemployeeid(id)
    {
        return  http.get(`/getempID/${id}`);

    }
    getemp(){
        return http.get("/emp");
    }
    sendPushToSingleUser(data){
        return  http.post("/sendSinglePush",data);

    }
    sendPushToMultiUser(data){
        return  http.post("/sendMultiPush",data);

    }


}
export default new MyServices();