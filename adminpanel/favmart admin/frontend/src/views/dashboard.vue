<template lang='html'>
  <section class='dashboard'>
    <!-- Page Title Header Starts-->

    <div class='row page-title-header'>
      <div class='col-12'>
        <div class='page-header'>
          <h4 class='page-title'>dashboard</h4>
          <div class='quick-link-wrapper w-100 d-md-flex flex-md-wrap'>

          </div>
        </div>
      </div>

    </div>
    <!-- Page Title Header Ends-->
    <div class='row'>
      <div class='col-md-12 grid-margin'>
        <div class='card'>
          <div class='card-body'>
            <div class='row'>
              <div class='col-lg-3 col-md-6'>
                <div class='d-flex'>
                  <div class='wrapper'>
                    <h3 class='mb-0 font-weight-semibold'>33</h3>
                    <p class='mb-0 font-weight-medium text-primary'>number of orders</p>
                  </div>
                </div>
              </div>
              <div class='col-lg-3 col-md-6 mt-md-0 mt-4'>
                <div class='d-flex'>
                  <div class='wrapper'>
                    <h3 class='mb-0 font-weight-semibold'></h3>
                    <p class='mb-0 font-weight-medium text-primary'></p>
                  </div>
                </div>
              </div>
              <div class='col-lg-3 col-md-6 mt-md-0 mt-4'>
                <div class='d-flex'>
                  <div class='wrapper'>
                    <h3 class='mb-0 font-weight-semibold'>155</h3>
                    <p class='mb-0 font-weight-medium text-primary'>number of Customer</p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class='row'>
      <div class='col-md-12 grid-margin stretch-card'>
        <div class='card'>
          <div class='card-body'>
            <h4 class='card-title mb-0'>number of Reads</h4>

            <div class='d-flex flex-column flex-lg-row'>

            </div>
            <sales-statistics-overview class='mt-5' :height="170"></sales-statistics-overview>
          </div>
        </div>
      </div>

    </div>
  </section>
</template>

<script lang='js'>
import statsLineGraph1 from '../components/charts/dashboard_1/stats-line-graph-1'
import statsLineGraph2 from '../components/charts/dashboard_1/stats-line-graph-2'
import statsLineGraph3 from '../components/charts/dashboard_1/stats-line-graph-3'
import statsLineGraph4 from '../components/charts/dashboard_1/stats-line-graph-4'
import salesStatisticsOverview from '../components/charts/dashboard_1/sales-statistics-overview'
import netProfit from '../components/charts/dashboard_1/net-Profit'
import totalRevenue from '../components/charts/dashboard_1/total-revenue'
import marketOverviewChart from '../components/charts/dashboard_1/market-overview-chart'
import totalTransaction from '../components/charts/dashboard_1/total-transaction'
import realtimeStatistics from '../components/charts/dashboard_1/realtime-statistics'
import usersDoughnutChart from '../components/charts/dashboard_1/usersDoughnutChart'

import JQuery from 'jquery'

let $ = JQuery
export default {
  name: 'dashboard',
  components: {
    statsLineGraph1,
    statsLineGraph2,
    statsLineGraph3,
    statsLineGraph4,
    salesStatisticsOverview,
    netProfit,
    totalRevenue,
    marketOverviewChart,
    totalTransaction,
    realtimeStatistics,
    usersDoughnutChart
  },
  methods: {
    toggleProBanner: function () {
      $('body').toggleClass('pro-banner-collapse')
    }
  }
}
</script>

<style scoped lang='scss'>
</style>
