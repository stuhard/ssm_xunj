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
                  v-if="user_group === '管理员' || (form['task_information_id'] && $check_field('set','patrol_personnel')) || (!form['task_information_id'] && $check_field('add','patrol_personnel'))"
                ></uni-data-select>
                <uni-data-select
                  v-model="form['patrol_personnel']"
                  :localdata="list_user_patrol_personnel"
                  :clear="false"
                  :disabled="true"
                  v-else-if="$check_field('get','patrol_personnel')" id="patrol_personnel"
                ></uni-data-select>
                  </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','task_title') || ($check_field('add','task_title') || $check_field('set','task_title'))" label="任务标题" name="task_title">
                            <uni-easyinput type="text" v-model="form['task_title']" v-if="user_group === '管理员' || (form['task_information_id'] && $check_field('set','task_title')) || (!form['task_information_id'] && $check_field('add','task_title'))" :disabled="disabledObj['task_title_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','task_title')">
                  {{ form['task_title'] }}
                </text>
                          </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','task_type') || ($check_field('add','task_type') || $check_field('set','task_type'))" label="任务类型" name="task_type">
                            <uni-easyinput type="text" v-model="form['task_type']" v-if="user_group === '管理员' || (form['task_information_id'] && $check_field('set','task_type')) || (!form['task_information_id'] && $check_field('add','task_type'))" :disabled="disabledObj['task_type_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','task_type')">
                  {{ form['task_type'] }}
                </text>
                          </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','task_attachment') || ($check_field('add','task_attachment') || $check_field('set','task_attachment'))" label="任务附件" name="task_attachment">
                    <!-- 查询权限 -->
                <view class="diy_field diy_file" v-if="$check_field('get','task_attachment')">
                  <button @click="download_file($fullUrl(form['task_attachment']))" class="btn" v-if="form['task_attachment']">下载文件</button>
                </view>
                  </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','release_date') || ($check_field('add','release_date') || $check_field('set','release_date'))" label="发布日期" name="release_date">
                    <uni-datetime-picker v-if="user_group === '管理员' || (form['task_information_id'] && $check_field('set','release_date')) || (!form['task_information_id'] && $check_field('add','release_date'))" v-model="form['release_date']" type="date" :disabled="disabledObj['release_date_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','release_date')">
                  {{ form['release_date'] }}
                </text>
                  </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','task_requirements') || ($check_field('add','task_requirements') || $check_field('set','task_requirements'))" label="任务要求" name="task_requirements">
                            <uni-easyinput type="text" v-model="form['task_requirements']" v-if="user_group === '管理员' || (form['task_information_id'] && $check_field('set','task_requirements')) || (!form['task_information_id'] && $check_field('add','task_requirements'))" :disabled="disabledObj['task_requirements_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','task_requirements')">
                  {{ form['task_requirements'] }}
                </text>
                          </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','task_content') || ($check_field('add','task_content') || $check_field('set','task_content'))" label="任务内容" name="task_content">
                    <uni-easyinput type="textarea" v-model="form['task_content']" v-if="user_group === '管理员' || (form['task_information_id'] && $check_field('set','task_content')) || (!form['task_information_id'] && $check_field('add','task_content'))" :disabled="disabledObj['task_content_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','task_content')">
                  {{ form['task_content'] }}
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
      field: "task_information_id",
      url_add: "~/api/task_information/add?",
      url_set: "~/api/task_information/set?",
      url_get_obj: "~/api/task_information/get_obj?",
      url_upload: "~/api/task_information/upload?",

      query: {
        "task_information_id": 0,
      },

      form: {
            "patrol_personnel": 0, // 巡查人员
                    "task_title":  '', // 任务标题
                    "task_type":  '', // 任务类型
                    "task_attachment":  '', // 任务附件
                    "release_date": this.$toTime(new Date().getTime(), "yyyy-MM-dd"),
                    "task_requirements":  '', // 任务要求
                    "task_content":  '', // 任务内容
                                "task_information_id": 0, // ID
                
              },
          disabledObj:{
                        "patrol_personnel_isDisabled": false,
                                "task_title_isDisabled": false,
                                "task_type_isDisabled": false,
                                "task_attachment_isDisabled": false,
                                "release_date_isDisabled": false,
                                "task_requirements_isDisabled": false,
                                "task_content_isDisabled": false,
                                  },
                                // 用户列表
            list_user_patrol_personnel: [],
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
            url: _self.$fullUrl('/api/task_information/upload?'),
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
            url: _self.$fullUrl('/api/task_information/upload?'),
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
                                              if (this.form["release_date"] && JSON.stringify(this.form["release_date"]).indexOf("-")===-1) {
        this.form["release_date"] = this.$toTime(parseInt(this.form["release_date"]), "yyyy-MM-dd")
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
     * 获取对象之后
     * @param {Object} json
     * @param {Object} func
     */
    get_obj_after(json, func){
                                              if (this.form["release_date"] && JSON.stringify(this.form["release_date"]).indexOf("-")===-1) {
        this.form["release_date"] = this.$toTime(parseInt(this.form["release_date"]),"yyyy-MM-dd")
      }
                        },

    is_view(){
      var bl = this.user_group == "管理员";

      if(!bl){
        bl = this.$check_action('/task_information/table','add');
        console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
      }
      if(!bl){
        bl = this.$check_action('/task_information/table','set');
        console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
      }
      if(!bl){
        bl = this.$check_action('/task_information/view','add');
        console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
      }
      if(!bl){
        bl = this.$check_action('/task_information/view','set');
        console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
      }
      if(!bl){
        bl = this.$check_action('/task_information/view','get');
        console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
      }

      console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

      return bl;
    },

  },
  created() {
            this.get_list_user_patrol_personnel();
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
