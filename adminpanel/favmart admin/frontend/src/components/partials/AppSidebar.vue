<template lang="html">
  <section class="app-sidebar">
    <nav class="sidebar sidebar-offcanvas" id="sidebar" >
      <ul class="nav">
        <li class="nav-item nav-profile">
          <a href="#" class="nav-link">
            <div class="profile-image">
              <img class="img-xs rounded-circle" src="../../assets/images/mo.jpeg" alt="profile image">
              <div class="dot-indicator bg-success"></div>
            </div>
            <div class="text-wrapper">
              <p class="profile-name">Abdighafar Mohamud</p>
              <p class="designation">abdighafar@gmail.com</p>
            </div>
          </a>
        </li>
        <li class="nav-item nav-category">Main Menu</li>
        <li class="nav-item">
          <a class="nav-link" v-b-toggle="'ui-basic'">
            <i class="menu-icon typcn typcn-coffee"></i>
            <span class="menu-title">Fruits</span>
            <i class="menu-arrow"></i>
          </a>
          <b-collapse id="ui-basic">
            <ul class="nav flex-column sub-menu">
              <li class="nav-item">
                <router-link class="nav-link" to="/addfruit/">Add Fruits</router-link>
              </li>
              <li class="nav-item">
                <router-link class="nav-link" to="/ManageFruits/">Manage Fruits</router-link>
              </li>
            </ul>
          </b-collapse>
        </li>
        <li class="nav-item">
          <a class="nav-link" v-b-toggle="'ui-basic1'">
            <i class="menu-icon typcn typcn-coffee"></i>
            <span class="menu-title">Vegetables</span>
            <i class="menu-arrow"></i>
          </a>
          <b-collapse id="ui-basic1">
            <ul class="nav flex-column sub-menu">
              <li class="nav-item">
                <router-link class="nav-link"  to="/addvegetabless/">Add Vegetables</router-link>
              </li>
              <li class="nav-item">
                <router-link class="nav-link" to="/ManageVegetable/">Manage Vegetables</router-link>
              </li>
            </ul>
          </b-collapse>
            
        </li>
          <li class="nav-item">
          <a class="nav-link" v-b-toggle="'emp'">
            <i class="menu-icon typcn typcn-document-add"></i>
            <span class="menu-title">Employee</span>
            <i class="menu-arrow"></i>
          </a>
          <b-collapse id="emp">
            <ul class="nav flex-column sub-menu">
              <li class="nav-item">
                <router-link class="nav-link" to="/addemployeee/">Add Employees</router-link>
              </li>
              <li class="nav-item">
                <router-link class="nav-link" to="/manageemp/">Manage Employees</router-link>
              </li>

            </ul>
          </b-collapse>
        </li>




        </li>
          <li class="nav-item">
          <a class="nav-link" v-b-toggle="'auth'">
            <i class="menu-icon typcn typcn-document-add"></i>
            <span class="menu-title">Orders</span>
            <i class="menu-arrow"></i>
          </a>
          <b-collapse id="auth">
            <ul class="nav flex-column sub-menu">
              <li class="nav-item">
                <router-link class="nav-link" to="/ManageOrders/">Manage Orders</router-link>
              </li>

            </ul>
          </b-collapse>
        </li>

        <li class="nav-item">
          <a class="nav-link" v-b-toggle="'auth1'">
            <i class="menu-icon typcn typcn-document-add"></i>
            <span class="menu-title">Users</span>
            <i class="menu-arrow"></i>
          </a>
          <b-collapse id="auth1">
            <ul class="nav flex-column sub-menu">
              <li class="nav-item">
                <router-link class="nav-link" to="/Adduser/">Manage Users</router-link>
              </li>
            </ul>
          </b-collapse>
        </li>
        <li class="nav-item">
          <a class="nav-link" v-b-toggle="'auth12'">
            <i class="menu-icon typcn typcn-document-add"></i>
            <span class="menu-title">System</span>
            <i class="menu-arrow"></i>
          </a>
          <b-collapse id="auth12">
            <ul class="nav flex-column sub-menu">
              
              <li class="nav-item">
                <router-link class="nav-link" to="/sendmessage/">Send Single User</router-link>
              </li>
               <li class="nav-item">
                <router-link class="nav-link" to="/sendmultiuser/">Send sendPushToMultiUser</router-link>
              </li>
              <li class="nav-item">
                <router-link class="nav-link" to="/viewfeedback/">feedback</router-link>
              </li>
            </ul>
          </b-collapse>
        </li>
      </ul>
    </nav>
  </section>
</template>

<script lang="js">
import JQuery from 'jquery'
// eslint-disable-next-line
let $ = JQuery
export default {
  name: 'app-sidebar',
  mounted () {
    const body = document.querySelector('body')
    // add class 'hover-open' to sidebar navitem while hover in sidebar-icon-only menu
    document.querySelectorAll('.sidebar .nav-item').forEach(function (el) {
      el.addEventListener('mouseover', function () {
        if (body.classList.contains('sidebar-icon-only')) {
          el.classList.add('hover-open')
        }
      })
      el.addEventListener('mouseout', function () {
        if (body.classList.contains('sidebar-icon-only')) {
          el.classList.remove('hover-open')
        }
      })
    })
  }
}
</script>

<style scoped lang="scss">
.app-sidebar {
}
</style>
