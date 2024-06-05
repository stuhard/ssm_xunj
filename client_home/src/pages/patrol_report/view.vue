<template>
  <view>
    <view class="container diy_view">
      <view>
        <view>
          <view class="">
            <uni-forms :modelValue="form"  v-if="is_view()">

              <uni-forms-item v-if="$check_field('get','patrol_personnel') || ($check_field('add','patrol_personnel') || $check_field('set','patrol_personnel'))" label="巡查人员" name="patrol_personnel">
                    <uni-data-select
                  id="form_patrol_personnel"
                  v-model="form['patrol_personnel']"
                  :localdata="list_user_patrol_personnel"
                  :clear="!disabledObj['patrol_personnel_isDisabled']"
                  :disabled="disabledObj['patrol_personnel_isDisabled']"
                  v-if="user_group === '管理员' || (form['patrol_report_id'] && $check_field('set','patrol_personnel')) || (!form['patrol_report_id'] && $check_field('add','patrol_personnel'))"
                ></uni-data-select>
                <uni-data-select
                  v-model="form['patrol_personnel']"
                  :localdata="list_user_patrol_personnel"
                  :clear="false"
                  :disabled="true"
                  v-else-if="$check_field('get','patrol_personnel')" id="patrol_personnel"
                ></uni-data-select>
                  </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','personnel_name') || ($check_field('add','personnel_name') || $check_field('set','personnel_name'))" label="人员姓名" name="personnel_name">
                            <uni-easyinput type="text" v-model="form['personnel_name']" v-if="user_group === '管理员' || (form['patrol_report_id'] && $check_field('set','personnel_name')) || (!form['patrol_report_id'] && $check_field('add','personnel_name'))" :disabled="disabledObj['personnel_name_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','personnel_name')">
                  {{ form['personnel_name'] }}
                </text>
                          </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','report_title') || ($check_field('add','report_title') || $check_field('set','report_title'))" label="上报标题" name="report_title">
                            <uni-easyinput type="text" v-model="form['report_title']" v-if="user_group === '管理员' || (form['patrol_report_id'] && $check_field('set','report_title')) || (!form['patrol_report_id'] && $check_field('add','report_title'))" :disabled="disabledObj['report_title_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','report_title')">
                  {{ form['report_title'] }}
                </text>
                          </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','report_type') || ($check_field('add','report_type') || $check_field('set','report_type'))" label="上报类型" name="report_type">
                            <uni-easyinput type="text" v-model="form['report_type']" v-if="user_group === '管理员' || (form['patrol_report_id'] && $check_field('set','report_type')) || (!form['patrol_report_id'] && $check_field('add','report_type'))" :disabled="disabledObj['report_type_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','report_type')">
                  {{ form['report_type'] }}
                </text>
                          </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','reporting_time') || ($check_field('add','reporting_time') || $check_field('set','reporting_time'))" label="上报时间" name="reporting_time">
                    <uni-datetime-picker v-if="user_group === '管理员' || (form['patrol_report_id'] && $check_field('set','reporting_time')) || (!form['patrol_report_id'] && $check_field('add','reporting_time'))" @change="changeLog($event,'reporting_time')" v-model="form['reporting_time']" type="datetime" :disabled="disabledObj['reporting_time_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','reporting_time')">
                  {{ form['reporting_time'] }}
                </text>
                  </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','reporting_location') || ($check_field('add','reporting_location') || $check_field('set','reporting_location'))" label="上报位置" name="reporting_location">
                            <uni-easyinput type="text" v-model="form['reporting_location']" v-if="user_group === '管理员' || (form['patrol_report_id'] && $check_field('set','reporting_location')) || (!form['patrol_report_id'] && $check_field('add','reporting_location'))" :disabled="disabledObj['reporting_location_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','reporting_location')">
                  {{ form['reporting_location'] }}
                </text>
                          </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','submit_images') || ($check_field('add','submit_images') || $check_field('set','submit_images'))" label="上报图片" name="submit_images">
                    <!-- 修改权限 -->
                <view class="diy_field diy_img" v-if="form['submit_images'] && $check_field('set','submit_images')">
                  <image v-if="disabledObj['submit_images_isDisabled']" :src="$fullUrl(form['submit_images'])" />
                  <image v-if="!disabledObj['submit_images_isDisabled']" :src="$fullUrl(form['submit_images'])" @click="change_img('submit_images')" />
                </view>
                <!-- 添加权限 -->
                <view class="diy_field diy_img" v-else-if="!form['submit_images'] && $check_field('add','submit_images')">
                  <view v-if="disabledObj['submit_images_isDisabled']" class="btn_add_img">
                    <text>+</text>
                  </view>
                  <view v-if="!disabledObj['submit_images_isDisabled']" class="btn_add_img" @click="change_img('submit_images')">
                    <text>+</text>
                  </view>
                </view>
                <!-- 查询权限 -->
                <view class="diy_field diy_img" v-else-if="$check_field('get','submit_images')">
                  <image :src="$fullUrl(form['submit_images'])" />
                </view>
                  </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','report_content') || ($check_field('add','report_content') || $check_field('set','report_content'))" label="上报内容" name="report_content">
                    <uni-easyinput type="textarea" v-model="form['report_content']" v-if="user_group === '管理员' || (form['patrol_report_id'] && $check_field('set','report_content')) || (!form['patrol_report_id'] && $check_field('add','report_content'))" :disabled="disabledObj['report_content_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','report_content')">
                  {{ form['report_content'] }}
                </text>
                  </uni-forms-item>


            </uni-forms>
            <view class="form_button">
              <button size="mini" type="primary" @click="submit()" class="primary_btn">提交</button>
              <button size="mini" @click="cancel()">取消</button>
            </view>
          </view>
        </view>
      </view>
    </view>
  </view>
</template>

<script>
import mixin from "@/libs/mixins/page.js";

export default {
  mixins: [mixin],
  data() {
    return {
      field: "patrol_report_id",
      url_add: "~/api/patrol_report/add?",
      url_set: "~/api/patrol_report/set?",
      url_get_obj: "~/api/patrol_report/get_obj?",
      url_upload: "~/api/patrol_report/upload?",

      query: {
        "patrol_report_id": 0,
      },

      form: {
            "patrol_personnel": 0, // 巡查人员
                    "personnel_name":  '', // 人员姓名
                    "report_title":  '', // 上报标题
                    "report_type":  '', // 上报类型
                    "reporting_time": this.$toTime(new Date().getTime(), "yyyy-MM-dd hh:mm:ss"),
                    "reporting_location":  '', // 上报位置
                    "submit_images":  '', // 上报图片
                    "report_content":  '', // 上报内容
                                "patrol_report_id": 0, // ID
                
              },
          disabledObj:{
                        "patrol_personnel_isDisabled": false,
                                "personnel_name_isDisabled": false,
                                "report_title_isDisabled": false,
                                "report_type_isDisabled": false,
                                "reporting_time_isDisabled": false,
                                "reporting_location_isDisabled": false,
                                "submit_images_isDisabled": false,
                                "report_content_isDisabled": false,
                                  },
                                // 用户列表
            list_user_patrol_personnel: [],
                        // 用户组
            group_user_patrol_personnel: "",
                                                                                                                                                                                      }
  },
  methods: {
    changeLog(v,value){
      this.form[value] = v
    },
    /**
     * 上传文件
     * @param {Object} param文件参数
     */
    change_file(key_name){
      var _self = this;
      // 选择图像方法
      uni.chooseFile({
        count: 1,
        sizeType: ['original', 'compressed'], //可以指定是原图还是压缩图，默认二者都有
        sourceType: ['album'], //从相册选择
        success: function(res) {
          const tempFilePaths = res.tempFilePaths;
          const uploadTask = uni.uploadFile({
            url: _self.$fullUrl('/api/patrol_report/upload?'),
            filePath: tempFilePaths[0],
            name: 'file',
            formData: {
              'i_want_to_customize': 'test'
            },
            header: {
              'x-auth-token': _self.$store.state.user.token
            },
            success: function(uploadFileRes) {
              var filename = JSON.parse(uploadFileRes.data).result.url
              _self.form[key_name] = filename
            }
          });

          uploadTask.onProgressUpdate(function(res) {
            _self.percent = res.progress;
            console.log('上传进度' + res.progress);
            console.log('已经上传的数据长度' + res.totalBytesSent);
            console.log('预期需要上传的数据总长度' + res.totalBytesExpectedToSend);
          });
        },
        error: function(e) {
          console.log(e);
        }
      });
    },
    /**
     * 上传图片
     * @param {Object} param文件参数
     */
    change_img(key_name){
      var _self = this;
      _self.upload_img_flag = false
      // 选择图像方法
      uni.chooseImage({
        count: 1,
        sizeType: ['original', 'compressed'], //可以指定是原图还是压缩图，默认二者都有
        sourceType: ['album'], //从相册选择
        success: function(res) {
          const tempFilePaths = res.tempFilePaths;
          const uploadTask = uni.uploadFile({
            url: _self.$fullUrl('/api/patrol_report/upload?'),
            filePath: tempFilePaths[0],
            name: 'file',
            formData: {
              'i_want_to_customize': 'test'
            },
            header: {
              'x-auth-token': _self.$store.state.user.token
            },
            success: function(uploadFileRes) {
              var filename = JSON.parse(uploadFileRes.data).result.url
              _self.form[key_name] = filename
            }
          });

          uploadTask.onProgressUpdate(function(res) {
            _self.percent = res.progress;
            console.log('上传进度' + res.progress);
            console.log('已经上传的数据长度' + res.totalBytesSent);
            console.log('预期需要上传的数据总长度' + res.totalBytesExpectedToSend);
          });
        },
        error: function(e) {
          console.log(e);
        }
      });
    },
    /**
     * 获取对象后获取缓存表单
     * @param {Object} json
     * @param {Object} func
     */
    get_obj_before(param){
      var form = uni.db.get("form");
      if (form) {
        delete(form.examine_state)
        delete(form.examine_reply)
        this.obj = uni.push(this.obj ,form);
        this.form = uni.push(this.form ,form);
      }
      var arr = []
      for (let key in form) {
        arr.push(key)
      }
      for (var i=0;i<arr.length;i++){
        this.disabledObj[arr[i] + '_isDisabled'] = true
      }
                                          if (this.form["reporting_time"] && JSON.stringify(this.form["reporting_time"]).indexOf("-")===-1) {
        this.form["reporting_time"] = this.$toTime(parseInt(this.form["reporting_time"]), "yyyy-MM-dd hh:mm:ss")
      }
                                      uni.db.del("form");
      return param;
    },
            /**
     * 获取巡查人员用户列表
     */
    async get_list_user_patrol_personnel() {
      // if(this.user_group !== "管理员" && this.form["patrol_personnel"] === 0) {
      //     this.form["patrol_personnel"] = this.user.user_id;
      // }
      var json = await this.$get("~/api/user/get_list?user_group=巡查人员");
      if(json.result && json.result.list){
        json.result.list.map((o) => this.list_user_patrol_personnel.push({value:o.user_id,text:o.nickname + '-' + o.username}));
      }
      else if(json.error){
        console.error(json.error);
      }
    },
            /**
     * 获取巡查人员用户组
     */
    async get_group_user_patrol_personnel() {
      this.form["patrol_personnel"] = this.user.user_id;
      var json = await this.$get("~/api/user_group/get_obj?name=巡查人员");
      if(json.result && json.result.obj){
        this.group_user_patrol_personnel = json.result.obj;
        this.get_user_session_patrol_personnel(this.form['patrol_personnel'])
      }
      else if(json.error){
        console.error(json.error);
      }
    },
    get_user_session_patrol_personnel(id){
      var _this = this;
      var user_id = {"user_id":id}
      var url = "~/api/"+_this.group_user_patrol_personnel.source_table+"/get_obj?"
      this.$get(url, user_id, function(res) {
        if (res.result && res.result.obj) {
          var arr = []
          for (let key in res.result.obj) {
            arr.push(key)
          }
          var arrForm = []
          for (let key in _this.form) {
            arrForm.push(key)
          }
          for (var i=0;i<arr.length;i++){
            if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
              for (var j = 0; j < arrForm.length; j++) {
                if (arr[i] === arrForm[j]) {
                  if (arr[i] !== "patrol_personnel") {
                    _this.form[arrForm[j]] = res.result.obj[arr[i]]
                    _this.disabledObj[arrForm[j] + '_isDisabled'] = true
                    break;
                  } else {
                    _this.disabledObj[arrForm[j] + '_isDisabled'] = true
                  }
                }
              }
            }
          }
        }
      });
    },
            
            
            
            
            
            
            
            
    
    /**
     * 获取对象之后
     * @param {Object} json
     * @param {Object} func
     */
    get_obj_after(json, func){
                                          if (this.form["reporting_time"] && JSON.stringify(this.form["reporting_time"]).indexOf("-")===-1) {
        this.form["reporting_time"] = this.$toTime(parseInt(this.form["reporting_time"]), "yyyy-MM-dd hh:mm:ss")
      }
                                    },

    is_view(){
      var bl = this.user_group == "管理员";

      if(!bl){
        bl = this.$check_action('/patrol_report/table','add');
        console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
      }
      if(!bl){
        bl = this.$check_action('/patrol_report/table','set');
        console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
      }
      if(!bl){
        bl = this.$check_action('/patrol_report/view','add');
        console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
      }
      if(!bl){
        bl = this.$check_action('/patrol_report/view','set');
        console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
      }
      if(!bl){
        bl = this.$check_action('/patrol_report/view','get');
        console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
      }

      console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

      return bl;
    },

  },
  created() {
            this.get_list_user_patrol_personnel();
            this.get_group_user_patrol_personnel();
                                                                      },
}
</script>

<style scoped>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}

.form_button{
  padding-bottom: 15px;
  display: flex;
}
.form_button button{
  width: 40%;
}
.query_select{
  border-color: rgb(229, 229, 229);
  background-color: rgb(255, 255, 255);
  border-radius: 4px;
  box-sizing: border-box;
  flex: 1;
  width: 100%;
  line-height: 2;
  font-size: 14px;
  height: 2.4em;
  min-height: 2.4em;
  display: block;
  outline:none;
}

.query_option{
  width: 100%;
}

.btn_add_img{
  color: #D3D3D3;
  text-align: center;
  border: 1px solid #eee;
  height: 5rem;
  width: 5rem;
  position: relative;
}
.btn_add_img text{
  font-size: 35px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%,-50%);
}
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}

.form_button {
  padding-bottom: 15px;
  display: flex;
}
.form_button button {
  width: 40%;
}
.query_select {
  border-color: rgb(229, 229, 229);
  background-color: rgb(255, 255, 255);
  border-radius: 4px;
  box-sizing: border-box;
  flex: 1;
  width: 100%;
  line-height: 2;
  font-size: 14px;
  height: 2.4em;
  min-height: 2.4em;
  display: block;
  outline: none;
}

.query_option {
  width: 100%;
}

.btn_add_img {
  color: #d3d3d3;
  text-align: center;
  border: 1px solid #eee;
  height: 5rem;
  width: 5rem;
  position: relative;
}
.btn_add_img text {
  font-size: 35px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}
/*新样式*/
.uni-forms{
padding-top:1rem;
}
.uni-forms-item {
	padding: 6px 10px;
    background: #f8f6fc;
}
.uni-forms .is-input-border{
	border: 0;
}
.container{
	    -webkit-box-shadow: 0px 0px 0px #888888;
	    box-shadow: 0px 0px 0px #888888;
}
.form_button .primary_btn{
		background-color: #22B8B8;
		color: #FFFFFF;
	}
</style>
