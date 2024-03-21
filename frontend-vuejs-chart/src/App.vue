<script setup>
  import BitcoinChartComponent from './components/BitcoinChartComponent.vue'
</script>

<template>
  <main class="container">
    <h1 class="title">Cryptocurrencies</h1>
    <BitcoinChartComponent/>
  </main>
</template>

<style type="text/css">
  .title {
    text-align: center;
  }
  .container {
    display: flex;
    flex-direction: column;
    align-items: center;
  }
</style>
