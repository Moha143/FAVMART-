import Vue from 'vue'
import Router from 'vue-router'

// Dashboard Components
import dashboard from '../views/dashboard'

//  Widgets Components
import mainView from '../mainView'

import buttons from '../views/basic-elements/buttons'
import dropdowns from '../views/basic-elements/dropdowns'
import typography from '../views/basic-elements/typography'

//  Chart Components
import chartjs from '../views/charts/chartjs'

//  Icons Components
import mdiIcons from '../views/icons/mdi-icons'

import login from '../views/samples/user-pages/login'
import register from '../views/samples/user-pages/register'

//  Table Components
import basicTables from '../views/tables/basic-table'
import addvegetabl from '../views/Vegetables/addvegetable'
import addfruit from '../views/fruits/addfruit'
import updatefruit from '../views/fruits/updatefruit'
import ManageFruits from '../views/fruits/ManageFruit'
import updateveg from '../views/Vegetables/updateveg'
import addve from '../views/Vegetables/manageveg'
import ManageOrders from '../views/orders/ManageOrders'
import adduser from '../views/users/adduser'
import sendmessage from '../views/bush/bush'
import sendmulti from '../views/bush/multi'
import viewfeedback from '../views/feedback/feedback'
import addemp from '../views/employee/addemp'
import manageemp from '../views/employee/manageemp'
import updateemployee from '../views/employee/updateemp'



Vue.use(Router)

export default new Router({
  linkActiveClass: 'active',
  scrollBehavior: () => ({ y: 0 }),
  mode: 'hash',
  routes: [{
    path: '/',
    redirect: '/register',
    component: mainView,
    children: [
      {
        
        path: '/register',
        name: 'register',
        component: dashboard
      },
      {
        path: '/buttons',
        name: 'buttons',
        component: buttons
      },
      {
        path: '/dropdowns',
        component: dropdowns
      },
      {
        path: '/typography',
        component: typography
      },
      {
        path: '/chartjs',
        component: chartjs
      },
      {
        path: '/mdiIcons',
        component: mdiIcons
      },
      {
        path: '/basic_table',
        name: 'basicTables',
        component: basicTables
      },
      {
        path: '/addfruit',
        name: 'add',
        component: addfruit
    
      },
      {
        path: '/ManageFruits',
        name: 'ManageFruits',
        component: ManageFruits
    
      },
      {
        path: '/updatefruit',
        name: 'updatefruit',
        component: updatefruit
    
      },
      {
        path: '/addvegetabless',
        name: 'add vegetable',
        component: addvegetabl
      },
      {
        path: '/ManageVegetable',
        name: 'add',
        component: addve
      },
      {
        path: '/updatevegetable',
        name: 'updatevegetable',
        component: updateveg
    
      },
 
      {
        path: '/sendmultiuser',
        name: 'multi',
        component: sendmulti
    
      },
      {
        path: '/addemployeee',
        name: 'addemployee',
        component: addemp
    
      },
      {
        path: '/manageemp',
        name: 'manageemp',
        component: manageemp
    
      },
      {
        path: '/updateemployee',
        name: 'updateemployee',
        component: updateemployee
    
      },
      

      {
        path: '/viewfeedback',
        name: 'viewfeedback',
        component: viewfeedback
    
      },
      
      {
        path: '/ManageOrders',
        name: 'ManageOrders',
        component: ManageOrders
    
      },
      {
        path: '/Adduser',
        name: 'Adduser',
        component: adduser
    
      },
      {
        path: '/sendmessage',
        name: 'sendmessage',
        component: sendmessage
    
      }
    
    ]
  },
  {
    path: '*',
    redirect: '/pages/error_404',
    component: {
      render (c) { return c('router-view') }
    },
    children: [
      {
        path: '/pages/login',
        component: login
      },
      {
        path: '/pages/register',
        component: register
      }
    ]},
  ]
})
