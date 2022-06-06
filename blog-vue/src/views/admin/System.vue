<template>
  <!--硬盘-->
  <Card class="disk">
    <template v-slot:head>
      <div>硬盘</div>
    </template>
    <template v-slot:body>
      <tr>
        <td>属性</td>
        <td>值</td>
      </tr>
      <tr>
        <td>总容量</td>
        <td>{{ this.diskInfo.allDisk }} G</td>
      </tr>
      <tr>
        <td>已用容量</td>
        <td>{{ this.diskInfo.useDisk }} G</td>
      </tr>
      <tr>
        <td>剩余容量</td>
        <td>{{ this.diskInfo.remainingDisk }} G</td>
      </tr>
    </template>
  </Card>
  <!--内存-->
  <Card class="memory">
    <template v-slot:head>
      <div>内存</div>
    </template>
    <template v-slot:body>
      <tr>
        <td>属性</td>
        <td>值</td>
      </tr>
      <tr>
        <td>总内存</td>
        <td>{{ this.memoryInfo.allMemory / 1024 }} G</td>
      </tr>
      <tr>
        <td>已使用内存</td>
        <td>{{ (this.memoryInfo.useMemory / 1024).toFixed(3) }} G</td>
      </tr>
      <tr>
        <td>剩余内存</td>
        <td>{{ ((this.memoryInfo.allMemory - this.memoryInfo.useMemory) / 1024).toFixed(3) }} G</td>
      </tr>
    </template>
  </Card>
  <!--cpu-->
  <Card class="cpu">
    <template v-slot:head>
      <div>cpu</div>
    </template>
    <template v-slot:body>
      <tr>
        <td>属性</td>
        <td>值</td>
      </tr>
      <tr>
        <td>CPU型号</td>
        <td>Intel(R) Core(TM) i7-10875H CPU @ 2.30GHz</td>
      </tr>
      <tr>
        <td>CPU核心数</td>
        <td>{{ this.osInfo.cpuNum }}</td>
      </tr>
      <tr>
        <td>CPU当前空闲率</td>
        <td>{{ this.osInfo.free }} %</td>
      </tr>
      <tr>
        <td>CPU系统使用率</td>
        <td>{{ this.osInfo.sys }} %</td>
      </tr>
      <tr>
        <td>CPU用户使用率</td>
        <td>{{ this.osInfo.used }} %</td>
      </tr>
      <tr>
        <td>CPU当前等待率</td>
        <td>{{ this.osInfo.wait }} %</td>
      </tr>
    </template>
  </Card>

</template>

<script>
import Card from "@/components/common/Card";
import axios from "axios";

export default {
  name: "System",
  components: {Card},
  data() {
    return {
      diskInfo: {
        allDisk: '',
        remainingDisk: '',
        useDisk: '',
      },
      memoryInfo: {
        allMemory: '',
        useMemory: '',
      },
      osInfo: {
        cpuNum: '',
        free: '',
        sys: '',
        used: '',
        wait: '',
      }
    }
  },
  created() {
    this.getDiskUsed();
    this.getMemoryInfo();
    this.getOsInfo();
  },
  methods: {
    // 获取磁盘信息
    getDiskUsed() {
      axios.get("system/getDiskUsed").then(res => {
        this.diskInfo = res.data
      })
    },
    // 获取内存信息
    getMemoryInfo() {
      axios.get("system/getMemoryInfo").then(res => {
        this.memoryInfo = res.data
      })
    },
    // 获取cpu信息
    getOsInfo() {
      axios.get("system/getOsInfo").then(res => {
        this.osInfo = res.data
      })
    }
  }
}
</script>

<style scoped>
.disk, .memory {
  width: 585px;
  height: 300px;
}

.cpu {
  width: 1210px;
  height: 500px;
}
.disk td,.memory td{
  width: 400px;
}
.cpu td{
  width: 600px;
}
td{
  border-bottom: 1px solid #d0d0d0;
}
</style>