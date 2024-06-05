<template>
	<view class="diy_edit page_patrol_report" id="patrol_report_edit">
		<view class='warp'>
			<view class='container'>
				<view class='row'>
						<view v-if="$check_field('set','patrol_personnel') || $check_field('add','patrol_personnel') || $check_field('get','patrol_personnel')" class="col-12 col-md-6 row-item">
						<view class="diy_title diy_text_row">
							<text>
								巡查人员:
							</text>
						</view>
						<view class="diy_field diy_down diy_text_row diy_select_flex">
							<uni-data-select
									id="form_patrol_personnel"
									v-model="form['patrol_personnel']"
									:localdata="list_user_patrol_personnel"
									:clear="!disabledObj['patrol_personnel_isDisabled']"
									:disabled="disabledObj['patrol_personnel_isDisabled']"
									v-if="(form['patrol_personnel'] && $check_field('set','patrol_personnel')) || (!form['patrol_personnel'] && $check_field('add','patrol_personnel'))"
							></uni-data-select>
							<text v-else-if="$check_field('get','patrol_personnel')">{{ form['patrol_personnel'] }}</text>
						</view>
					</view>
							<view v-if="$check_field('set','personnel_name') || $check_field('add','personnel_name') || $check_field('get','personnel_name')" class="col-12 col-md-6 row-item">
						<view class="diy_title diy_text_row">
							<text>
								人员姓名:
							</text>
						</view>
								<!-- 文本 -->
									<view class="diy_field diy_text diy_text_row">
							<input type="text" id="form_personnel_name" v-model="form['personnel_name']" placeholder="请输入人员姓名" v-if="(form['personnel_name'] && $check_field('set','personnel_name')) || (!form['personnel_name'] && $check_field('add','personnel_name'))" :disabled="disabledObj['personnel_name_isDisabled']" />
							<text v-else-if="$check_field('get','personnel_name')">{{ form['personnel_name'] }}</text>
						</view>
										</view>
							<view v-if="$check_field('set','report_title') || $check_field('add','report_title') || $check_field('get','report_title')" class="col-12 col-md-6 row-item">
						<view class="diy_title diy_text_row">
							<text>
								上报标题:
							</text>
						</view>
								<!-- 文本 -->
									<view class="diy_field diy_text diy_text_row">
							<input type="text" id="form_report_title" v-model="form['report_title']" placeholder="请输入上报标题" v-if="(form['report_title'] && $check_field('set','report_title')) || (!form['report_title'] && $check_field('add','report_title'))" :disabled="disabledObj['report_title_isDisabled']" />
							<text v-else-if="$check_field('get','report_title')">{{ form['report_title'] }}</text>
						</view>
										</view>
							<view v-if="$check_field('set','report_type') || $check_field('add','report_type') || $check_field('get','report_type')" class="col-12 col-md-6 row-item">
						<view class="diy_title diy_text_row">
							<text>
								上报类型:
							</text>
						</view>
								<!-- 文本 -->
									<view class="diy_field diy_text diy_text_row">
							<input type="text" id="form_report_type" v-model="form['report_type']" placeholder="请输入上报类型" v-if="(form['report_type'] && $check_field('set','report_type')) || (!form['report_type'] && $check_field('add','report_type'))" :disabled="disabledObj['report_type_isDisabled']" />
							<text v-else-if="$check_field('get','report_type')">{{ form['report_type'] }}</text>
						</view>
										</view>
							<view v-if="$check_field('set','reporting_time') || $check_field('add','reporting_time') || $check_field('get','reporting_time')" class="col-12 col-md-6 row-item">
						<view class="diy_title diy_text_row">
							<text>
								上报时间:
							</text>
						</view>
								<!-- 日长 -->
						<view class="diy_field diy_datetime diy_text_row">
							<uni-datetime-picker :disabled="disabledObj['reporting_time_isDisabled']" type="datetime" id="form_reporting_time" v-model="form['reporting_time']" placeholder="请输入上报时间" v-if="(form['reporting_time'] && $check_field('set','reporting_time')) || (!form['reporting_time'] && $check_field('add','reporting_time'))" />
							<text v-else-if="$check_field('get','reporting_time')">{{ form['reporting_time'] }}</text>
						</view>
							</view>
							<view v-if="$check_field('set','reporting_location') || $check_field('add','reporting_location') || $check_field('get','reporting_location')" class="col-12 col-md-6 row-item">
						<view class="diy_title diy_text_row">
							<text>
								上报位置:
							</text>
						</view>
								<!-- 文本 -->
									<view class="diy_field diy_text diy_text_row">
							<input type="text" id="form_reporting_location" v-model="form['reporting_location']" placeholder="请输入上报位置" v-if="(form['reporting_location'] && $check_field('set','reporting_location')) || (!form['reporting_location'] && $check_field('add','reporting_location'))" :disabled="disabledObj['reporting_location_isDisabled']" />
							<text v-else-if="$check_field('get','reporting_location')">{{ form['reporting_location'] }}</text>
						</view>
										</view>
							<view v-if="$check_field('set','submit_images') || $check_field('add','submit_images') || $check_field('get','submit_images')" class="col-12 col-md-6 row-item">
						<view class="diy_title diy_text_row">
							<text>
								上报图片:
							</text>
						</view>
								<!-- 图片 -->
						<!-- 修改权限 -->
						<view class="diy_field diy_img diy_text_row" v-if="form['submit_images'] && $check_field('set','submit_images')">
							<image v-if="disabledObj['submit_images_isDisabled']" :src="$fullUrl(form['submit_images'])" />
							<image v-if="!disabledObj['submit_images_isDisabled']" :src="$fullUrl(form['submit_images'])" @click="change_img('submit_images')" />
						</view>
						<!-- 添加权限 -->
						<view class="diy_field diy_img diy_text_row" v-else-if="!form['submit_images'] && $check_field('add','submit_images')">
							<view v-if="disabledObj['submit_images_isDisabled']" class="btn_add_img">
								<text>+</text>
							</view>
							<view v-if="!disabledObj['submit_images_isDisabled']" class="btn_add_img diy_text_row" @click="change_img('submit_images')">
								<text>+</text>
							</view>
						</view>
						<!-- 查询权限 -->
						<view class="diy_field diy_img diy_text_row" v-else-if="$check_field('get','submit_images')">
							<image :src="$fullUrl(form['submit_images'])" />
						</view>
						<uni-icons style="display: none;" class="forward" type="forward" id="form_img_submit_images"></uni-icons>
							</view>
							<view v-if="$check_field('set','report_content') || $check_field('add','report_content') || $check_field('get','report_content')" class="col-12 col-md-6 row-item">
						<view class="diy_title diy_text_row">
							<text>
								上报内容:
							</text>
						</view>
								<!-- 多文本 -->
						<view class="diy_field diy_desc diy_text_row">
							<textarea id="form_report_content" v-model="form['report_content']" v-if="(form['report_content'] && $check_field('set','report_content')) || (!form['report_content'] && $check_field('add','report_content'))" :disabled="disabledObj['report_content_isDisabled']"/>
							<text v-else-if="$check_field('get','report_content')">{{ form['report_content'] }}</text>
						</view>
							</view>
	
				</view>
				<view class="row">
					<view class="col-12">
						<view class="btn_box">
							<button class="btn_submit primary_btn" @click="submit()">提交</button>
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
	mixins:[mixin],
	components:{},
	data(){
		return{
			url_get_obj:"~/api/patrol_report/get_obj?",
			url_add:"~/api/patrol_report/add?",
			url_set:"~/api/patrol_report/set?",

			// 登录权限
			oauth: {
				"signIn": true,
				"user_group": []
			},

			// 查询条件
			query: {
					"patrol_personnel": 0,
						"personnel_name": "",
						"report_title": "",
						"report_type": "",
						"reporting_time": "",
						"reporting_location": "",
						"submit_images": "",
						"report_content": "",
					"patrol_report_id": 0
			},

			obj: {
					"patrol_personnel": 0, // 巡查人员
						"personnel_name":  '', // 人员姓名
						"report_title":  '', // 上报标题
						"report_type":  '', // 上报类型
						"reporting_time": this.$toTime(new Date().getTime(), "yyyy-MM-dd hh:mm:ss"),
						"reporting_location":  '', // 上报位置
						"submit_images":  '', // 上报图片
						"report_content":  '', // 上报内容
					"patrol_report_id": 0,

			},

			// 表单字段
			form: {
					"patrol_personnel": 0, // 巡查人员
						"personnel_name":  '', // 人员姓名
						"report_title":  '', // 上报标题
						"report_type":  '', // 上报类型
						"reporting_time": this.$toTime(new Date().getTime(), "yyyy-MM-dd hh:mm:ss"),
						"reporting_location":  '', // 上报位置
						"submit_images":  '', // 上报图片
						"report_content":  '', // 上报内容
					"patrol_report_id": 0,
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
															
			field:"patrol_report_id",
			table_key:"patrol_report",

		}
	},
	methods: {
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
				async get_user_session_patrol_personnel(){
			var _this = this;
			var json = await this.$get("~/api/user_group/get_obj?name=巡查人员");
			if(json.result && json.result.obj){
				var source_table = json.result.obj.source_table;
				var user_id = _this.$store.state.user.user_id;
				if (user_id){
					var url = "~/api/"+source_table+"/get_obj?"
					this.$get(url, {"user_id":_this.$store.state.user.user_id}, function(res) {
						if (res.result && res.result.obj) {
							var arr = []
							for (let key in res.result.obj) {
								arr.push(key)
							}
							var arrForm = []
							for (let key in _this.form) {
								arrForm.push(key)
							}
							_this.form["patrol_personnel"] = user_id
							_this.disabledObj['patrol_personnel' + '_isDisabled'] = true
							for (var i=0;i<arr.length;i++){
                if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
                  for (var j = 0; j < arrForm.length; j++) {
                    if (arr[i] === arrForm[j]) {
                      if (arr[i] !== "patrol_personnel") {
                        _this.form[arrForm[j]] = res.result.obj[arr[i]]
                        _this.disabledObj[arrForm[j] + '_isDisabled'] = true
                        break;
                      }
                    }
                  }
                }
							}
						}
					});
				}
			}
			else if(json.error){
				console.error(json.error);
			}
		},
	
	
				
	
				
	
				
	
				
	
				
	
				
	
				
	
			change_file(key_name){
			var _self = this;
				this.$chooseFile().then(res=>{
					console.log(res)

						const uploadTask = uni.uploadFile({
							url: _self.$fullUrl("/api/feedback/upload?"),
							filePath: res[0].path,
							name: "file",
							formData: {
								i_want_to_customize: "test",
							},
							header: {
								"x-auth-token": _self.$store.state.user.token,
							},
							success: function(uploadFileRes) {
								console.log(uploadFileRes)
								var filename = JSON.parse(uploadFileRes.data).result.url;
								_self.form[key_name] = filename;
							},
						});

						uploadTask.onProgressUpdate(function(res) {
							_self.percent = res.progress;
							console.log("上传进度" + res.progress);
							console.log("已经上传的数据长度" + res.totalBytesSent);
							console.log(
								"预期需要上传的数据总长度" + res.totalBytesExpectedToSend
							);
						});

				})
		},
		change_img(key_name) {
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
							'patrol_report': 'test'
						},
						header: {
							'x-auth-token': _self.$store.state.user.token
						},
						success: function(uploadFileRes) {
							var filename = JSON.parse(uploadFileRes.data).result.url
							var img_url = filename
							_self.form[key_name] = img_url
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
		 * 获取对象后获取缓存表单
		 * @param {Object} json
		 * @param {Object} func
		 */
		get_obj_after(json ,func){
			var form = uni.db.get("form");
			var obj = Object.assign({} ,form ,this.obj);
			if (form) {
				this.obj = uni.push(this.obj ,obj);
			}
			if (form) {
				this.form = uni.push(this.form ,form);
			}
			if(func){
				func(json);
			}
		},

	},
	onLoad(){
				this.get_user_session_patrol_personnel();
				this.get_list_user_patrol_personnel();
																							},
}
</script>

<style scoped>
	input{
		font-size: 10px;
	}

	.form_edit {
		background-color: #fff;
		margin-bottom: 0.5rem;
		padding: 1rem;
		font-size: 10px;
	}

	.item {
		display: flex;
		padding: 0.2rem 0;
	}

	.left_text {
		flex: 0 0 25%;
		display: flex;
		align-items: center;
	}

	.right_text {
		flex: 0 0 75%;
		border-bottom: 1px solid #eee;
	}
	.right_text.btn_warp{
		border-bottom: 0;
	}

	.btn_submit {
		text-align: center;
		background-color: #fff;
		padding: 0.3rem;
		margin: 0.1rem 1rem;
		border: 1px solid #eee;
		border-radius: 0.5rem;
	}

	.btn_submit:hover {
		opacity: 0.5;
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




</style>
<style scoped>
/*新样式*/
.diy_text_row {
  display: inline-block;
}
.container {
	margin-top:1rem ;
	padding: 1rem;
    -webkit-box-shadow: 0px 0px 0px #888888;
    box-shadow: 0px 0px 0px #888888;
}
.primary_btn{
		background-color: #22B8B8;
		color: #FFFFFF;
	}
	.btn_submit{
		padding: 0;
		margin-top:1rem ;
	}
	.row-item {
		padding: 10px 10px;
	    background: #f8f6fc;
		margin-bottom: 1rem;
	}
	.diy_field{
		padding-left: 1rem;
	}
	.diy_title{
		align-items: center;
        font-size: 14px;
        color: #333;
	}

	.row-item{
		display: flex !important;
		align-items: baseline;
	}
	.diy_select_flex{
		flex: 1;
	}
	.query_select{
		flex: 1;
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
</style>

