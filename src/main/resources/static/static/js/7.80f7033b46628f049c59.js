webpackJsonp([7],{"8c2u":function(e,t){},bvRv:function(e,t,o){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var n=o("Xxa5"),a=o.n(n),i=o("mtWM"),r=o.n(i),s=o("D2Ra"),l=o("NYxO");function c(e){return function(){var t=e.apply(this,arguments);return new Promise(function(e,o){return function n(a,i){try{var r=t[a](i),s=r.value}catch(e){return void o(e)}if(!r.done)return Promise.resolve(s).then(function(e){n("next",e)},function(e){n("throw",e)});e(s)}("next")})}}var u={name:"",components:{},data:function(){return{banMa:[],count:0,banMa1:[],banMa11:[],pointType:0,videoUrl:"",imageUrl:"",videoId:"",canBiaoZhu:!1,pointColor:"red",pointSize:6,websock:"",wsPath:"ws://305451f414c5fc79.natapp.cc:8252/status",type:1,persent:0,isShow:"none",ruleForm:{video:"",uploadFile:"",name:""},loading:!1,show:!0,receive:!1}},methods:(Object.assign||function(e){for(var t=1;t<arguments.length;t++){var o=arguments[t];for(var n in o)Object.prototype.hasOwnProperty.call(o,n)&&(e[n]=o[n])}return e})({},Object(l.b)(["choiceMenuId","setUploadFile"]),{handleChange:function(e,t){this.ruleForm.uploadFile=e.raw,this.uploadVideo()},beforeUpload:function(e){},initWebSocket:function(){"WebSocket"in window?this.websock=new WebSocket(this.wsPath):alert("当前浏览器 Not support websocket"),this.websock.onmessage=this.websocketonmessage,this.websock.onopen=this.websocketonopen,this.websock.onerror=this.websocketonerror,this.websock.onclose=this.websocketclose,window.onbeforeunload=function(){this.websocket.close()}},websocketonopen:function(){console.log("已连接")},websocketonerror:function(){this.getWebsock&&this.initWebSocket()},websocketonmessage:function(e){var t=this;console.log("后台："),console.log(e),e.data.indexOf("success")>0&&(this.websock.close(),this.loading=!1,this.receive=!0,this.$confirm("视频已处理完，是否前往查看?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning",center:!0}).then(function(){var e=t.videoId;t.$router.replace({name:"mainPage",query:{videoId:JSON.stringify(e)}})}).catch(function(){t.$router.replace({name:"more"})}))},websocketsend:function(e){},websocketclose:function(e){console.log("断开连接",e)},uploadVideo:function(){var e=this;return c(a.a.mark(function t(){var o,n,i;return a.a.wrap(function(t){for(;;)switch(t.prev=t.next){case 0:(o=e.ruleForm.uploadFile)&&(e.persent=0,(n=new FormData).append("file",o),i={headers:{"Content-Type":"multipart/form-data"},withCredentials:!0,transformRequest:[function(e){return e}],onUploadProgress:function(t){var o=t.loaded/t.total*100|0;100!=o&&(e.persent=o)}},e.type=2,r.a.post("http://119.3.215.235:8252/video/upload",n,i).then(function(t){var o=t.data;console.log(o),0==o.status?(o=o.data,e.videoId=o.videoId,e.videoUrl=o.videoUrl,e.imageUrl=o.imgUrl,e.type=3,e.$refs.video.src=e.videoUrl,console.log(o.videoId),e.$refs.video.load(),e.isShow="block"):(e.type=1,e.$message({message:"上传失败",type:"error",duration:"1000"}))}).catch(function(e){console.log(e)}));case 2:case"end":return t.stop()}},t,e)}))()},uploadBiaoZhu:function(){var e=this;return c(a.a.mark(function t(){var o,n,i,r;return a.a.wrap(function(t){for(;;)switch(t.prev=t.next){case 0:if(o=e.banMa,n=e.videoUrl,i=e.videoId,(r=new FormData).append("message",o),r.append("videoId",i),r.append("videoUrl",n),!(o.length>0&&n&&i)){t.next=13;break}return JSON.stringify(o),t.next=9,Object(s.k)(r);case 9:0==t.sent.status?i&&(e.loading=!0):e.$message({message:"上传标注信息失败，请重试",type:"error",duration:"1200"}),t.next=14;break;case 13:e.$message({message:"请输入完整信息再上传",type:"warning",duration:"1200"});case 14:case"end":return t.stop()}},t,e)}))()},sortByX:function(e,t){return e.x,t.x,e.x-t.x},sortByY:function(e,t){return e.y,t.y,e.y-t.y},startBiaoZhu:function(){this.pointColor="red",this.pointType=1,this.canBiaoZhu=!0},startBiaoZhu1:function(){this.pointColor="yellow",this.pointType=2,this.canBiaoZhu=!0},endBiaoZhu:function(){if(console.log(this.banMa1),this.banMa1.length%4==0&&this.banMa11.length%4==0){this.banMa=[],this.canBiaoZhu=!1,this.banMa1.sort(this.sortByX),this.banMa11.sort(this.sortByX);for(var e=this.banMa1.length/4,t=(this.banMa11.length,[]),o=this.banMa1,n=(this.banMa11,0);n<e;n++){var a,i,r,s,l=o.slice(4*n,4*(n+1)).sort(this.sortByY);l[0].x>l[1].x?(a=l[0],s=l[1]):(a=l[1],s=l[0]),l[2].x>l[3].x?(i=l[2],r=l[3]):(i=l[3],r=l[2]),l[0]=a,l[1]=i,l[2]=r,l[3]=s;for(var c=[],u=0;u<4;u++){var d=[];d.push(l[u].x),d.push(l[u].y),c.push(d)}t.push(c)}this.banMa.push(t),console.log(this.banMa)}else this.$message({type:"warning",duration:1200,message:"您还未完整标注实线或路口"})},createMarker:function(e,t){var o=this,n=document.createElement("div");n.className="marker",n.id="marker"+this.count,t=t+document.getElementById("myBiaoZhu").offsetTop-this.pointSize/2,e=e+document.getElementById("myBiaoZhu").offsetLeft-this.pointSize/2,n.style.width=this.pointSize+"px",n.style.height=this.pointSize+"px",n.style.backgroundColor=this.pointColor,n.style.left=e+"px",n.style.top=t+"px",n.oncontextmenu=function(e){if(console.log(e.target.style.backgroundColor),o.canBiaoZhu){var t=e.target.id;return document.getElementById("myBiaoZhuDiv").removeChild(n),"red"==e.target.style.backgroundColor?o.banMa1=o.banMa1.filter(function(e){return e.id!=t.slice(6,t.length)}):"yellow"==e.target.style.backgroundColor&&(o.banMa11=o.banMa11.filter(function(e){return e.id!=t.slice(6,t.length)})),e&&e.preventDefault?e.preventDefault():window.event.returnValue=!1,!1}},document.getElementById("myBiaoZhuDiv").appendChild(n)}}),mounted:function(){var e=this;document.getElementById("myBiaoZhu").oncontextmenu=function(e){return e&&e.preventDefault?e.preventDefault():window.event.returnValue=!1,!1},document.getElementById("myBiaoZhu").onmousedown=function(t){if(2!==(t=t||window.event).button&&e.canBiaoZhu){var o=t.offsetX||t.layerX,n=t.offsetY||t.layerY,a=document.querySelector("#myBiaoZhu");a.clientWidth,a.clientHeight;e.count++,1==e.pointType?e.banMa1.push({pointType:2,id:e.count,x:2*o,y:2*n}):2==e.pointType&&e.banMa11.push({pointType:2,id:e.count,x:2*o,y:2*n}),console.log(2*o,2*n),e.createMarker(o,n)}},this.choiceMenuId("upload"),this.initWebSocket()},beforeDestroy:function(){this.websock&&this.websock.close()}},d={render:function(){var e=this,t=e.$createElement,o=e._self._c||t;return o("div",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],staticClass:"myBiaoZhu",attrs:{id:"myBiaoZhuDiv","element-loading-text":"视频解析中","element-loading-spinner":"el-icon-loading","element-loading-background":"rgba(0, 0, 0, 0.8)"}},[o("div",{staticClass:"container-fluid"},[e.show?o("div",{staticClass:"upload-element uploadDiv"},[o("el-form",{ref:"ruleForm",attrs:{model:e.ruleForm,"label-width":"100px"}},[o("el-form-item",{ref:"uploadElement",attrs:{label:"上传视频:",required:"",prop:"video"}},[e._e(),e._v(" "),o("el-upload",{ref:"upload",staticClass:"avatar-uploader",staticStyle:{float:"left"},attrs:{"show-file-list":!1,action:"#","before-upload":e.beforeUpload,"on-change":e.handleChange,"auto-upload":!1,accept:"video/*",data:e.ruleForm}},[1===e.type?o("i",{staticClass:"el-icon-plus avatar-uploader-icon"}):e._e(),e._v(" "),2===e.type?o("el-progress",{attrs:{type:"circle",percentage:e.persent}}):e._e(),e._v(" "),3===e.type?o("i",{staticClass:"el-icon-refresh-right avatar-uploader-icon"}):e._e()],1),e._v(" "),o("video",{ref:"video",style:{display:e.isShow},attrs:{controls:"",aspectRatio:"16:9",fluid:""}})],1),e._v(" "),e._e(),e._v(" "),o("el-form-item",{directives:[{name:"show",rawName:"v-show",value:e.imageUrl,expression:"imageUrl"}],attrs:{label:"图片标注:",prop:"biaozhu",required:""}},[o("el-button",{attrs:{type:"primary",plain:"",size:"small"},on:{click:e.startBiaoZhu}},[e._v("开始标注")]),e._v(" "),o("el-button",{attrs:{type:"success",plain:"",size:"small"},on:{click:e.endBiaoZhu}},[e._v("标注完成")])],1),e._v(" "),o("div",{directives:[{name:"show",rawName:"v-show",value:e.imageUrl,expression:"imageUrl"}],staticClass:"biaoZhuDiv"},[o("img",{staticClass:"biaoZhuTu",attrs:{id:"myBiaoZhu",src:e.imageUrl,alt:""}})]),e._v(" "),o("el-form-item",{staticStyle:{"margin-top":"15px","margin-bottom":"0!important"},attrs:{label:""}},[o("el-button",{attrs:{type:"primary",size:"small"},on:{click:e.uploadBiaoZhu}},[e._v("上传标注信息")])],1)],1)],1):e._e()])])},staticRenderFns:[]};var p=o("VU/8")(u,d,!1,function(e){o("8c2u")},null,null);t.default=p.exports}});