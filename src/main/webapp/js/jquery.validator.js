(function() {
	var isFunction = jQuery.isFunction, indexOf = (Array.prototype.indexOf) ? function(
			a, val) {
		return Array.prototype.indexOf.call(a, val);
	}
			: function(a, val) {
				var i = 0, len;
				for (len = a.length; i < len; i = i + 1) {
					if (a[i] === val) {
						return i;
					}
				}

				return -1;
			};

			doc = document,

			RULES = {
				require : /.+/,
				agreelicense : /^1$/,
				authcode : /^[0-9a-zA-Z]{4}$/,
				username : /^[\u4E00-\u9FA5A-Za-z0-9_\ ]{3,20}$/i,
				password : /^[a-zA-Z0-9\_\-\~\!\%\*\@\#\$\&\.\(\)\[\]\{\}\<\>\?\\\/\'\"]{3,20}$/,
				number : /^\d+$/,
				money : /^(([1-9]\d*)|(([0-9]{1}|[1-9]+)\.[0-9]{1,2}))$/,
				per : /^(?:[1-9][0-9]?|100)(?:\.[0-9]{1,2})?$/,
				email : /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/,
				phone : /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/,
				mobile : /^((\(\d{2,3}\))|(\d{3}\-))?((13\d{9})|(15[35890]\d{8})|(186\d{8}))$/,
				url : /^http:\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"])*$/,
				ip : /^(0|[1-9]\d?|[0-1]\d{2}|2[0-4]\d|25[0-5]).(0|[1-9]\d?|[0-1]\d{2}|2[0-4]\d|25[0-5]).(0|[1-9]\d?|[0-1]\d{2}|2[0-4]\d|25[0-5]).(0|[1-9]\d?|[0-1]\d{2}|2[0-4]\d|25[0-5])$/,
				currency : /^\d+(\.\d+)?$/,
				zip : /^[1-9]\d{5}$/,
				qq : /^[1-9]\d{4,8}$/,
				english : /^[A-Za-z]+$/,
				chinese : /^[\u0391-\uFFE5]+$/,
				integer : /^[-\+]?\d+$/,
				'double' : /^[-\+]?\d+(\.\d+)?$/,
				idcard : /(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3})|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])(\d{4}|\d{3}[x]))$/
			},
			TIPS = {
				require : {
					"tips" : "该信息为必填项，请填写！",
					"warn" : "对不起，必填信息不能为空，请填写！"
				},
				agreelicense : {
					"tips" : "请确认浏览过服务协议！",
					"warn" : "未确认浏览过服务协议！"
				},
				authcode : {
					"tips" : "请对照右方填写验证码。",
					"warn" : "验证码不正确！",
					"error" : "验证码不正确！"
				},
				username : {
					"tips" : "5~20个字符，由中文、英文字母和下划线组成。",
					"warn" : "对不起，用户名格式不正确。这确的格式如：“robert_yao” 或者 “创业街商户”。",
					"error" : "对不起，该用户名已经被注册。请更换一个用户名。"
				},
				password : {
					"tips" : "3~20个字符，由英文字母，数字和<a name='spChar' title='\_ \- \~ \! \% \* \@ \# \$ \& \. \( \) \[ \] \{ \} \< \> \? \\ \/ \' \"'>特殊符号<\/a>组成。",
					"warn" : "对不起，密码长度不够或者包含非法字符。",
					"error" : "对不起，两次输入的密码不一致！"
				},
				number : {
					"tips" : "请输入数字！",
					"warn" : "对不起，您填写的用户名包含非法字符。"
				},
				date : {
					"tips" : "请填写日期！",
					"warn" : "对不起，您填写的日期格式不正确，正确格式为：1989-09-23"
				},
				money : {
					"tips" : "请输入金额！",
					"warn" : "对不起，您填写的金额格式不正确。金额必须是数字，例如：“60” 或者 “60.59”。"
				},
				per : {
					"tips" : "请输入百分比！",
					"warn" : "对不起，您填写的百分比格式不正确！"
				},
				email : {
					"tips" : "请输入您的常用邮箱！如：example@exmaple.com",
					"warn" : "请输入正确的邮箱地址！",
					"error" : "该邮箱地址已经被注册。"
				},
				phone : {
					"tips" : "请输入可以联系到您常用的电话号码！",
					"warn" : "对不起，您填写的电话号码格式不正确！正确的电话号码如：010 - 85789999。"
				},
				mobile : {
					"tips" : "请输入可以联系到您的手机号码！",
					"warn" : "对不起，您填写的手机号码格式不正确！正确的手机号码如：13912345678。"
				},
				url : {
					"tips" : "请输入网站地址！",
					"warn" : "对不起，您填写的网站地址格式不正确！正确的网站地址如：http://www.yourdomain.com/。"
				},
				ip : {
					"tips" : "请输入IP地址！",
					"warn" : "对不起，您填写的IP地址格式不正确！正确的IP地址如：192.168.1.1。"
				},
				currency : {
					"tips" : "请输入金额！",
					"warn" : "对不起，您填写的金额格式不正确！正确的金额格式如：19.00。"
				},
				zip : {
					"tips" : "请输入邮政编码！",
					"warn" : "对不起，您填写的邮政编码格式不正确！正确的邮政编码如：430051。"
				},
				qq : {
					"tips" : "请输入您的QQ号！",
					"warn" : "对不起，您填写的QQ号格式不正确！正确的QQ号如：64392719。"
				},
				english : {
					"tips" : "请输入英文字母！",
					"warn" : "对不起，您填写的内容含有英文字母（A-Z,a-z）以外的非法字符！"
				},
				chinese : {
					"tips" : "请输入中文字符！",
					"warn" : "对不起，您填写的内容含非法字符！"
				},
				integer : {
					"tips" : "请输入整数！",
					"warn" : "对不起，您填写的内容不是整数！"
				},
				'double' : {
					"tips" : "请输入浮点数！",
					"warn" : "对不起，您填写的内容格式不正确！"
				},
				idcard : {
					"tips" : "请输入身份证号码！",
					"warn" : "对不起，您填写的身份证号码格式不正确！正确的格式如：420105198809223213。"
				}
			}, NOT_SAME = '对不起，两次输入的密码不一致！', SUCC = '成功！',
			LVL = 'security-level', TIP = 'tip', TIP_NORM = 'tip-normal',
			TIP_PASS = 'tip-pass', TIP_ERROR = 'tip-error',
			TIP_PENDING = 'tip-pending', TIP_BOX = 'tip-box',
			TIP_BOX_NORM = 'tip-box-normal', TIP_BOX_PASS = 'tip-box-pass',
			TIP_BOX_ERROR = 'tip-box-error', ITEM_PASS = 'item-pass',
			ITEM_ERROR = 'item-error', RESULT_NONE = 'NONE',
			RESULT_ERROR = 'ERROR', RESULT_PASS = 'PASS',
			RESULT_PENDING = 'PENDING',

			chk = function(reg, str) {
				return reg.test(str);
			};

	/**
	 * Validator 表单验证类
	 * 
	 * @class Validator
	 * @static
	 * @author Yaohaixiao
	 * @version 1.1.0
	 */
	jQuery.Validator = {
		indexOf : indexOf,
		/**
		 * 提示信息框
		 * 
		 * @property msgTip {HTMLElement}
		 */
		msgTip : null,
		/**
		 * 常用的验证规则
		 * 
		 * @property RULES
		 */
		RULES : RULES,
		TIPS : TIPS,
		/**
		 * 是否存在错误
		 * 
		 * @property hasError (false：没有错误/true：存在错误)
		 */
		hasError : false,
		/**
		 * 添加的要验证的表单元素
		 * 
		 * @property items {Array}
		 */
		items : [],

		results : {},

		/**
		 * 添加的验证初始化信息
		 * 
		 * @property options {Array}
		 */
		options : [],

		/**
		 * 初始化验证器，确定是否给表单提交绑定自定义的处理功能
		 * 
		 * <code>
		 * <pre>
		 * // 按钮是submit类型
		 * Validator.setup({
		 * 	&quot;form&quot; : &quot;frmReg&quot;,
		 * 	&quot;callback&quot; : function() {
		 * 		alert('自定义处理方法');
		 * 	}
		 * });
		 * Validator.setup({
		 * 	&quot;form&quot; : &quot;frmReg&quot;
		 * });
		 * 
		 * // 按钮是button类型
		 * Validator.setup({
		 * 	&quot;submit&quot; : &quot;frmReg&quot;,
		 * 	&quot;callback&quot; : function() {
		 * 		alert('自定义处理方法');
		 * 	}
		 * });
		 * Validator.setup({
		 * 	&quot;submit&quot; : &quot;frmReg&quot;
		 * });
		 * </pre>
		 * </code>
		 * 
		 * @method setup
		 * @static
		 * @param {Object}
		 *            config
		 * @return {Validator}
		 */
		setup : function(config) {
			var form = null, submit = null, callback = null, validator = this;

			// 获得验证的表单或者提交按钮
			if (config.form) {
				this.form = $('#' + config.form)[0];
				form = $(this.form);
			} else {
				if (config.submit) {
					submit = $('#' + config.submit);
				}
			}

			// 初始化没有成功
			if (!form[0] && !submit[0]) {
				return false;
			}

			// 获取表单提交时绑定的自定义处理函数
			// if(config.callback && isFunction(config.callback)){
			// callback = config.callback;
			// }

			// 给表单提交按钮绑定验证处理
			// if(form[0]){// 提交按钮是sumbit类型
			// form.submit(function(evt){
			// var result = validator.validateBeforeSubmit.call(validator,
			// callback);
			// if(!result){
			// return true;
			// }else{
			// evt.preventDefault();
			// evt.stopPropagation();
			// return false;
			// }
			// // evt.preventDefault();
			// // evt.stopPropagation();
			// });
			// }
			// else{
			// if(submit[0]){// 提交按钮是button类型
			// submit.click(function(evt){
			// var result = validator.validateBeforeSubmit.call(validator,
			// callback);
			// if(result){
			// return true;
			// }else{
			// return false;
			// }
			// // evt.preventDefault();
			// // evt.stopPropagation();
			// });
			// }
			// }

			return this;
		},
		/**
		 * 给表单绑定验证处理，验证全部通过了就提交表单
		 * 
		 * <code>
		 * <pre>
		 * Validator.submit();
		 * 
		 * Validator.submit(function() {
		 * 	alert('这是一个自定义提交处理函数');
		 * });
		 * </pre>
		 * </code>
		 * 
		 * @method sumbit
		 * @static
		 * @param {Function}
		 *            callback - 可选
		 * @return {Validator}
		 */
		validateBeforeSubmit : function() {
			var form = this.getForm(), i = 0, len = 0, item = null, results = this.results, needValidate = false;

			// 将绑定的验证内容全部验证一遍
			this.hasError = false;
			for ( var target in results) {
				var result = results[target];
				if (result && result.status == RESULT_ERROR) {
					this.hasError = true;
					break;
				} else if (result && result.status == RESULT_NONE) {
					needValidate = true;
				}
			}
			if (!this.hasError && needValidate) {
				this.validateAll();
			}
			for (target in results) {
				result = results[target];
				if (result && result.status == RESULT_ERROR) {
					this.hasError = true;
					break;
				}
			}
			return this.hasError;

			// for (len = this.items.length; i < len; i += 1) {
			// item = this.items[i];
			// if($(item).hasClass(ITEM_ERROR)){
			// this.hasError = true;
			// break;
			// }
			// }

			// // 如果验证全部通过
			// if (!this.hasError) {
			// // 如果绑定了自定义提交处理，执行自定义处理
			// if (callback) {
			// callback();
			// }
			//				
			// if (!hasError) {
			// if (form) {
			// $(form).submit(function(){
			// $(this).ajaxSubmit();
			// return false;
			// });
			// }
			// }
			// return false;
			// }
			// else {
			// return false;
			// }
			//			
			// return this;
		},
		/**
		 * 添加验证配置项到验证器
		 * 
		 * <code>
		 * <pre>
		 *     Validator.add({
		 *         &quot;target&quot;: &quot;username&quot;,
		 *         &quot;rule&quot;: &quot;username&quot;
		 *     });
		 *     
		 *     Validator.add({
		 *         &quot;target&quot;: &quot;username&quot;,
		 *         &quot;rule&quot;: &quot;username&quot;
		 *     }).add{{
		 *         &quot;target&quot;: &quot;password&quot;,
		 *         &quot;rule&quot;: &quot;password&quot;
		 *     }};
		 * </pre>
		 * </code>
		 * 
		 * @method add
		 * @static
		 * @param {Object}
		 *            option
		 * @return {Validator}
		 */
		add : function(option) {
			if (!option) {
				return false;
			}

			var item = this.getItem(option);
			var tip = TIPS[option.rule];

			this.options.push(option);
			this.items.push(item);
			this.results[option.target] = {
				status : "NONE",
				msg : tip.tips
			};

			this.bindHandle(option);

			return this;
		},
		/**
		 * 移除添加的验证信息
		 * 
		 * @method remove
		 * @static
		 * @param {Object}
		 *            option
		 * @return {Validator}
		 */
		remove : function(option) {
			var item = null, index = indexOf(this.options, option);

			this.options.splice(index, 1);

			if (this.options.length > 0) {
				item = $('#' + option.target)[0];
				this.items.splice(index, 1);

				// removeListener(item, 'focus', this.styleValidate);
				// removeListener(item, 'blur', this.validateItem);

				this.hideTip();
				this.hasError = false;
				this.styleItem(item);
			}

			return this;
		},
		/**
		 * 给需要验证的表单项绑定事件处理函数
		 * 
		 * <code>
		 * <pre>
		 * Validator.bindHandle({
		 * 	&quot;target&quot; : &quot;username&quot;,
		 * 	&quot;rule&quot; : &quot;username&quot;
		 * });
		 * </pre>
		 * </code>
		 * 
		 * @method bindHandle
		 * @static
		 * @param {Object}
		 *            option
		 * @return {Validator}
		 */
		bindHandle : function(option) {
			var validator = this, rule = option.rule.toLowerCase(), item = this
					.getItem(option), boxCnt = this.getBoxCnt(option), plus = option.plus
					|| null;

			if (boxCnt) {
				boxCnt.onmouseover = function() {
					var result = validator.results[option.target];
					if (result.status != RESULT_PASS) {
						validator.showTipBox(item, result.msg, option);
					} else {
						validator.hideTipBox();
					}
				};
				boxCnt.onblur = function() {
					validator.hideTipBox();
					// setTimeOut( function(){
					// validator.validateItem.call(validator, option);
					// }, 2);
					validator.validateItem.call(validator, option);
				};

			} else {

				item.onfocus = function(ipt) {
					return function() {
						var msg = validator.getTip(option), tipCnt = $('#'
								+ option.tipCnt)[0];

						if (ipt.id === 'userId') {
							if (ipt.value === ipt.defaultValue) {
								ipt.value = '';
							}
							ipt.style.color = '#000';
						}

						// validator.showTip(ipt, msg, tipCnt);

						if (plus) {
							validator.bindKeyupHandle.call(validator, option);
						}
						validator.hideTipBox();
					};
				}(item);

				item.onblur = function() {
					validator.hideTipBox();
					validator.validateItem.call(validator, option);
				};

				item.onmouseover = function() {
					var result = validator.results[option.target];
					if (result.status != RESULT_PASS) {
						validator.showTipBox(item, result.msg, option);
					} else {
						validator.hideTipBox();
					}
				};
			}

			return this;
		},
		/**
		 * 给文本输入框绑定keyup处理函数
		 * 
		 * <code>
		 * <pre>
		 * Validator.bindKeyupHandle({
		 * 	&quot;target&quot; : &quot;username&quot;,
		 * 	&quot;rule&quot; : &quot;username&quot;
		 * });
		 * </pre>
		 * </code>
		 * 
		 * @method bindKeyupHandle
		 * @param {Object}
		 *            option
		 * @return {Validator}
		 */
		bindKeyupHandle : function(option) {
			var validator = this, item = this.getItem(option), plus = option.plus, lvl = option.level
					|| LVL;

			// 如果有密码级别验证
			if (plus) {
				// 根据自定义的函数验证
				if (isFunction(plus)) {
					$(item).keyup(plus);
				} else {
					// 没有设置自定义级别验证，使用默认的级别验证
					$(item).keyup(function() {
						validator.checkLevel.call(validator, item, lvl);
					});
				}
			}

			return this;
		},
		/**
		 * 验证所有需要验证的表单信息
		 * 
		 * <code>
		 * <pre>
		 * Validator.validateAll();
		 * </pre>
		 * </code>
		 * 
		 * @method validateAll
		 * @static
		 * @return {Validator}
		 */
		validateAll : function() {
			var options = this.options, i = 0, len = 0;

			// 一个个的验证需要验证的表单信息
			for (len = options.length; i < len; i += 1) {
				// 验证单个表单项的信息
				this.validateItem(options[i]);
			}
		},
		/**
		 * 验证某一项表单信息
		 * 
		 * <code>
		 * <pre>
		 * Validator.validateItem({
		 * 	&quot;target&quot; : &quot;username&quot;,
		 * 	&quot;rule&quot; : &quot;username&quot;,
		 * 	&quot;tips&quot; : &quot;5&tilde;20个字符，由中文、英文字母和下划线组成。&quot;,
		 * 	&quot;warn&quot; : &quot;对不起，用户名格式不正确。这确的格式如：“robert_yao” 或者 “创业街商户”。&quot;
		 * });
		 * </pre>
		 * </code>
		 * 
		 * @method validateItem
		 * @param {Object}
		 *            option
		 */
		validateItem : function(option) {
			var msg = '', item = this.getItem(option), val = item.value, rule = option.rule
					.toLowerCase(), ignoreDefaultChk = option.ignoreDefaultChk || false, tipCnt = option.tipCnt
					|| null;
			var result = this.results[option.target];
			// 如果需要先验证常规信息
			if (!ignoreDefaultChk) {
				// 开始验证RULES中的常规规则
				this.validate(rule, val, option.target);

				if (result.status != RESULT_ERROR) {
					// 忽略常规验证，就直接使用用户自定义的规则
					this.validateCustomize(option);

					if (result.status == RESULT_ERROR) {
						msg = option.error || TIPS[rule].error;
					} else {
						msg = SUCC;
					}
				} else {
					msg = option.warn || TIPS[rule].warn;
				}
			} else {
				// 忽略常规验证，就直接使用用户自定义的规则
				this.validateCustomize(option);

				if (result.status == RESULT_ERROR) {
					msg = option.error || TIPS[rule].error;
				} else {
					msg = SUCC;
				}
			}

			this.renderResult(option, msg);
			// this.styleValidate(item, msg, tipCnt);
		},

		/**
		 * 根据验证的结果，设置验证表单项和提示的样式
		 * 
		 * <code>
		 * <pre>
		 * Validator.validate('number', '20');
		 * // Returns false
		 * 
		 * Validator.validate('number', 20);
		 * // Returns true
		 * </pre>
		 * </code>
		 * 
		 * @method validate
		 * @static
		 * @param {String}
		 *            rule
		 * @param {String|Number}
		 *            val
		 */
		renderResult : function(option, msg) {
			this.styleTipCnt(option, msg);
		},

		styleTipCnt : function(option, msg) {
			var tipCnt = this.getTipCnt(option);
			var result = this.results[option.target];
			if (result.status == RESULT_ERROR) {
				if ($(tipCnt).hasClass(TIP_NORM)) {
					$(tipCnt).removeClass(TIP_NORM);
				}
				if ($(tipCnt).hasClass(TIP_PASS)) {
					$(tipCnt).removeClass(TIP_PASS);
				}
				if ($(tipCnt).hasClass(TIP_PENDING)) {
					$(tipCnt).removeClass(TIP_PENDING);
				}
				$(tipCnt).addClass(TIP_ERROR);
				result.msg = msg;
			} else if (result.status == RESULT_PASS) {
				if ($(tipCnt).hasClass(TIP_NORM)) {
					$(tipCnt).removeClass(TIP_NORM);
				}
				if ($(tipCnt).hasClass(TIP_ERROR)) {
					$(tipCnt).removeClass(TIP_ERROR);
				}
				if ($(tipCnt).hasClass(TIP_PENDING)) {
					$(tipCnt).removeClass(TIP_PENDING);
				}
				$(tipCnt).addClass(TIP_PASS);
				result.msg = null;
			} else if (result.status == RESULT_PENDING) {
				if ($(tipCnt).hasClass(TIP_NORM)) {
					$(tipCnt).removeClass(TIP_NORM);
				}
				if ($(tipCnt).hasClass(TIP_ERROR)) {
					$(tipCnt).removeClass(TIP_ERROR);
				}
				if ($(tipCnt).hasClass(TIP_PASS)) {
					$(tipCnt).removeClass(TIP_PASS);
				}
				$(tipCnt).addClass(TIP_PENDING);
				result.msg = null;
			}
			$(tipCnt).show();
		},

		/**
		 * 常规信息验证
		 * 
		 * <code>
		 * <pre>
		 * Validator.validate('number', '20');
		 * // Returns false
		 * 
		 * Validator.validate('number', 20);
		 * // Returns true
		 * </pre>
		 * </code>
		 * 
		 * @method validate
		 * @static
		 * @param {String}
		 *            rule
		 * @param {String|Number}
		 *            val
		 */
		validate : function(rule, val, target) {
			var i = 0, len = 0, REG_STR = '';
			var result = this.results[target];

			rule = rule.toLowerCase();

			// 如果验证规规则是date，使用isDate方法验证
			if (rule === 'date') {
				result.status = !this.isDate(val) ? RESULT_ERROR : RESULT_PASS;
			} else {
				if (rule === 'image') {
					result.status = !this.isImage(val) ? RESULT_ERROR
							: RESULT_PASS;
				} else {
					// 如果只有一个规则
					if (rule.indexOf('||') === -1) {
						REG_STR = RULES[rule];
						result.status = !chk(REG_STR, val) ? RESULT_ERROR
								: RESULT_PASS;
					} else {
						// 如果有多条规则，只需要满足一种情况时
						if (rule.indexOf('||') > -1) {
							// 获得全部的验证规则
							rule = rule.split('||');
							for (len = rule.length; i < len; i += 1) {
								// 获得某一项验证的规则（正则表达式）
								REG_STR = RULES[rule[i]];
								// 有一条是正确的
								if (chk(REG_STR, val)) {
									result.status = RESULT_PASS;
									// 返回结果为真，退出验证
									break;
								} else {
									result.status = RESULT_ERROR;
								}
							}
						} else {
							// 如果有多条验证规则，且必须满足时
							if (rule.indexOf('&&') > -1) {
								rule = rule.split('&&');
								for (len = rule.length; i < len; i += 1) {
									REG_STR = RULES[rule[i]];
									if (!chk(REG_STR, val)) {
										result.status = RESULT_ERROR;
										break;
									} else {
										result.status = RESULT_PASS;
									}
								}
							}
						}
					}
				}
			}
		},
		/**
		 * 验证自定义的规则
		 * 
		 * <code>
		 * <pre>
		 * Validator.validateCustomize({
		 * 	&quot;target&quot; : &quot;username&quot;,
		 * 	&quot;rule&quot; : &quot;username&quot;,
		 * 	&quot;tips&quot; : &quot;5&tilde;20个字符，由中文、英文字母和下划线组成。&quot;,
		 * 	&quot;warn&quot; : &quot;对不起，用户名格式不正确。这确的格式如：“robert_yao” 或者 “创业街商户”。&quot;,
		 * 	&quot;actionURL&quot; : &quot;http://www.sbi.com/chkusername.jsp&quot;
		 * });
		 * </pre>
		 * </code>
		 * 
		 * @method validateCustomize
		 * @static
		 * @param {Object}
		 *            option
		 */
		validateCustomize : function(option) {

			var ajaxURL = option.action, toEl = $('#' + option.to)[0], fn = option.fn, rule = option.rule
					.toLowerCase(), item = null, val = '', msg = '', result = this.results[option.target];

			if (!result || (!ajaxURL && !toEl && !fn)) {
				return false;
			}

			item = this.getItem(option);
			val = item.value;

			// Ajax 验证
			if (ajaxURL) {
				this.ajaxValidate.call(this, option);
			} else {
				// 密码的一致性验证
				if (toEl) {
					if (toEl.value !== val) {
						result.status = RESULT_ERROR;
					} else {
						result.status = RESULT_PASS;
					}
				} else {
					// 自定义函数的验证
					if (fn) {
						result.status = !fn() ? RESULT_ERROR : RESULT_PASS;
					}
				}
			}
		},
		/**
		 * 获得tip的提示信息
		 * 
		 * <code>
		 * <pre>
		 * Validator.getTip({
		 * 	&quot;target&quot; : &quot;username&quot;,
		 * 	&quot;rule&quot; : &quot;username&quot;
		 * });
		 * </pre>
		 * </code>
		 * 
		 * @method
		 * @param {Object}
		 *            option
		 */
		getTip : function(option) {
			var item = this.getItem(option), rule = option.rule.toLowerCase(), msg = option.tips
					|| TIPS[rule].tips, chkedTip = item.rel || '';

			if (chkedTip) {
				msg = chkedTip;
			}

			return msg;
		},
		/**
		 * 显示提示框
		 * 
		 * <code>
		 * <pre>
		 * Validator.showTip(document.getElementById('username'), '成功')
		 * </pre>
		 * </code>
		 * 
		 * @method shotTip
		 * @static
		 * @param {HTMLElement}
		 *            item
		 * @param {String}
		 *            msg
		 * @param {HTMLElement}
		 *            tipCnt {Optional}
		 */
		showTipBox : function(item, msg, option) {
			var top = 0, left = 0;

			// 如果还没有创建提示信息框，则创建一个
			if (!this.msgTip) {
				this.msgTip = doc.createElement('span');
				$(this.msgTip).addClass("tip-box");
				doc.body.appendChild(this.msgTip);
			}
			// 显示提示框
			$(this.msgTip).show();
			// 将信息写入到提示框中
			this.msgTip.innerHTML = msg;
			// 计算提示框的位置
			var boxCnt = this.getBoxCnt(option);
			if (boxCnt) {
				top = $(boxCnt).offset().top;
				left = $(boxCnt).offset().left + $(boxCnt).width() + 10;
			} else {
				top = $(item).offset().top;
				left = $(item).offset().left + item.offsetWidth + 2;
			}
			$(this.msgTip).css({
				'top' : top + 'px',
				'left' : left + 'px'
			});

			// 给提示框添加样式
			this.styleTipBox(option);
			// this.styleTip(item);
		},
		/**
		 * 隐藏提示框
		 * 
		 * <code>
		 * <pre>
		 * Validator.hideTip();
		 * </pre>
		 * </code>
		 * 
		 * @method hideTip
		 * @static
		 */
		hideTipBox : function() {
			var tip = this.msgTip;

			if (tip) {
				if (tip) {
					$(tip).hide();
				}
			}
		},
		/**
		 * 根据验证的结果，给tip提示设置样式
		 * 
		 * @method styleTip
		 * @static
		 * @param {String}
		 *            target item
		 */
		styleTipBox : function(option) {
			var result = this.results[option.target];
			var tip = this.msgTip;
			if (result.status == RESULT_ERROR) {
				if ($(tip).hasClass(TIP_BOX_PASS)) {
					$(tip).removeClass(TIP_BOX_PASS);
				}
				if ($(tip).hasClass(TIP_BOX_NORM)) {
					$(tip).removeClass(TIP_BOX_NORM);
				}
				$(tip).addClass(TIP_BOX_ERROR);
			} else if (result.status == RESULT_PASS) {
				if ($(tip).hasClass(TIP_BOX_ERROR)) {
					$(tip).removeClass(TIP_BOX_ERROR);
				}
				if ($(tip).hasClass(TIP_BOX_NORM)) {
					$(tip).removeClass(TIP_BOX_NORM);
				}
				$(tip).addClass(TIP_BOX_PASS);
			} else if (result.status == RESULT_NONE
					|| result.status == RESULT_PENDING) {
				if ($(tip).hasClass(TIP_BOX_ERROR)) {
					$(tip).removeClass(TIP_BOX_ERROR);
				}
				if ($(tip).hasClass(TIP_BOX_PASS)) {
					$(tip).removeClass(TIP_BOX_PASS);
				}
				$(tip).addClass(TIP_BOX_NORM);
			}
		},
		/**
		 * 根据验证的结果，给tip提示设置样式
		 * 
		 * @method styleTip
		 * @static
		 * @param {Object}
		 *            item
		 */
		styleTip : function(item) {
			var tip = this.msgTip, clsName = '';

			// 已经通过了验证
			if ($(item).hasClass(ITEM_PASS)) {
				$(tip).removeClass(TIP_ERROR);
				$(tip).addClass(TIP_PASS);
				clsName = TIP_ERROR;
			} else {
				// 没有通过了验证
				if ($(item).hasClass(ITEM_ERROR)) {
					$(tip).removeClass(TIP_PASS);
					$(tip).addClass(TIP_ERROR);
					clsName = TIP_PASS;
				} else {
					// 之前有样式
					if (clsName) {
						$(tip).removeClass(className);
					} else {
						tip.className = "validator-tip";
					}
				}
			}
		},
		/**
		 * 根据验证结果，给对应的输入框添加样式
		 * 
		 * <code>
		 * <pre>
		 * Validator.styleItem(getEl('username'), false);
		 * </pre>
		 * </code>
		 * 
		 * @method styleItem
		 * @static
		 * @param {HTMLElement}
		 *            item
		 * @param {Boolean}
		 *            hasError - optional
		 * @return {Validator}
		 */
		styleItem : function(item) {
			var error = this.hasError;

			if (!error) {
				$(item).removeClass(ITEM_ERROR);
				$(item).addClass(ITEM_PASS);
			} else {
				$(item).removeClass(ITEM_PASS);
				$(item).addClass(ITEM_ERROR);
			}

			return this;
		},
		/**
		 * 根据验证结果，给验证表单项和提示框设置样式
		 * 
		 * <code>
		 * <pre>
		 * var user = getEl('username');
		 * Validator.styleValidate(user, '对不起，两次密码输入不一致！');
		 * </pre>
		 * </code>
		 * 
		 * @method styleValidate
		 * @static
		 * @param {HTMLElement}
		 *            item
		 * @param {String}
		 *            msg
		 * @param {HTMLElement}
		 *            tipCnt
		 * @return {Validator}
		 */
		styleValidate : function(item, msg, tipCnt) {

			item.rel = msg;

			// 如果验证没有通过，现实错误的提示信息
			if (this.hasError) {
				// 设置验证后表单项的样式
				this.styleItem(item);

				// 现实提示信息
				this.showTip(item, msg, tipCnt);
			} else {
				// 设置验证后表单项的样式
				this.styleItem(item);

				// 验证通过，隐藏错误提示信息
				this.hideTip(item);
			}

			return this;
		},
		/**
		 * 检测上传文件的扩展名
		 * 
		 * <code>
		 * <pre>
		 * Validator.isImage('img/bg.gif');
		 * // Returns true;
		 * 
		 * Validator.isImage('img/reg.asp');
		 * // Returns false;
		 * </pre>
		 * </code>
		 * 
		 * @method isImage
		 * @static
		 * @param {String}
		 *            string
		 * @returns {Boolean}
		 */
		isImage : function(string) {
			var filename = string.replace(/.*(\/|\\)/, ""), fileExt = (/[.]/
					.exec(filename)) ? /[^.]+$/.exec(filename.toLowerCase())
					: '';
			if ((fileExt !== 'jpg') && (fileExt !== 'gif')
					&& (fileExt !== 'jpeg') && (fileExt !== 'png')
					&& (fileExt !== 'bmp')) {
				return false;
			}
			return true;
		},
		/**
		 * 验证是否为正确的日期格式
		 * 
		 * <code>
		 * <pre>
		 * Validator.isDate('2011-03-24');
		 * // Return true;
		 * 
		 * Validator.isDate('good');
		 * // Return false;
		 * </pre>
		 * </code>
		 * 
		 * @method isDate
		 * @static
		 * @param {Date|Any}
		 *            date - 日期、任意值
		 * @param {Object}
		 *            reObj - 自定义的日期验证正则表达式
		 * @param {String}
		 *            format - 自定义的日期格式
		 * @return {Boolean}
		 */
		isDate : function(date, reObj, format) {
			format = format || 'yyyy-MM-dd';
			var input = date, i, o = {}, d = new Date();
					f1 = format.split(/[^a-z]+/gi),
					f2 = input.split(/\D+/g),
					f3 = format.split(/[a-z]+/gi),
					f4 = input.split(/\d+/g),
					len = f1.length,
					len1 = f3.length,
					reVal = false,
					s = function(s1, s2, s3, s4, s5) {
						s4 = s4 || 60, s5 = s5 || 2;
						var reVal = s3;
						if (s1 != undefined && s1 != '' || !isNaN(s1)) {
							reVal = s1 * 1;
						}
						if (s2 != undefined && s2 != '' && !isNaN(s2)) {
							reVal = s2 * 1;
						}
						return (reVal == s1 && s1.length != s5 || reVal > s4) ? -10000
								: reVal;
					};

			if (len != f2.length || len1 != f4.length) {
				return false;
			}

			for (i = 0; i < len1; i += 1) {
				if (f3[i] != f4[i]) {
					return false;
				}
			}

			for (i = 0; i < len; i += 1) {
				o[f1[i]] = f2[i];
			}

			o.yyyy = s(o.yyyy, o.yy, d.getFullYear(), 9999, 4);
			o.MM = s(o.MM, o.M, d.getMonth() + 1, 12);
			o.dd = s(o.dd, o.d, d.getDate(), 31);
			o.hh = s(o.hh, o.h, d.getHours(), 24);
			o.mm = s(o.mm, o.m, d.getMinutes());
			o.ss = s(o.ss, o.s, d.getSeconds());
			o.ms = s(o.ms, o.ms, d.getMilliseconds(), 999, 3);

			if (o.yyyy + o.MM + o.dd + o.hh + o.mm + o.ss + o.ms < 0) {
				return false;
			}

			if (o.yyyy < 100) {
				o.yyyy += (o.yyyy > 30 ? 1900 : 2000);
			}

			d = new Date(o.yyyy, o.MM - 1, o.dd, o.hh, o.mm, o.ss, o.ms);
			reVal = d.getFullYear() == o.yyyy && d.getMonth() + 1 == o.MM
					&& d.getDate() == o.dd && d.getHours() == o.hh
					&& d.getMinutes() == o.mm && d.getSeconds() == o.ss
					&& d.getMilliseconds() == o.ms;

			return reVal && reObj ? d : reVal;
		},
		/**
		 * 检测密码的安全级别,并设置相应级别提示的背景图片
		 * 
		 * <code>
		 * <pre>
		 * Validator.checkLevel(document.getElementById('password'), 'security-level');
		 * </pre>
		 * </code>
		 * 
		 * @method checkLevel
		 * @static
		 * @param {HTMLElement}
		 *            item
		 * @param {String}
		 *            level
		 */
		checkLevel : function(item, level) {
			var n = this.getLevel(item), percent = [ 0, 22, 50, 75, 100 ], lvlZone = $('#'
					+ level);
			lvlZone.css('backgroundPosition', 'left ' + percent[n] + '%');
		},
		/**
		 * 获得密码输入框的密码的安全级别
		 * 
		 * <code>
		 * <pre>
		 * Validator.getLevel(document.getElementById('password'));
		 * // Returns 0 | 1 | 2 | 3 | 4
		 * </pre>
		 * </code>
		 * 
		 * @method getLevel
		 * @static
		 * @param {HTMLElement}
		 *            item
		 * @param {String}
		 *            level
		 * @return {Number}
		 */
		getLevel : function(item) {
			var v = item.value, l = v.length, min = 6, level = 0;
			if (l < min) {
				if (l > 0) {
					level = 1;
				}
			} else {
				if (/^(\d{6,9}|[a-z]{6,9}|[A-Z]{6,9})$/.test(v)) {
					level = 1;
				} else {
					if (/^[^a-z\d]{6,8}$/i.test(v)
							|| !/^(\d{6,9}|[a-z]{6,9}|[A-Z]{6,9})$/.test(v)) {
						level = 2;
					}
				}
				if (!/^(([A-Z]*|[a-z]*|\d*|[-_\~!@#\$%\^&\*\.\(\)\[\]\{\}<>\?\\\/\'\"]*)|.{0,5})$|\s/
						.test(v)) {
					level = l < 10 ? 3 : 4;
				}
			}
			return level;
		},
		/**
		 * 自定义的ajax验证，验证用户名，邮箱，昵称是否存在时可以使用
		 * 
		 * @method ajaxValidate
		 * @static
		 * @param {Object}
		 *            option
		 */
		ajaxValidate : function(option) {
			var item = this.getItem(option), ajaxURL = option.action || '', ajaxParam = option.param
					|| '', ajaxData = encodeURIComponent(item.value), ajaxFn = option.ajaxFn
					|| null, tipCnt = option.tipCnt || null, result = this.results[option.target], validator = this;

			if (ajaxURL) {
				// 如果设置了自定义的ajax验证方法，执行自定义的ajax方法
				if (isFunction(ajaxFn)) {
					ajaxFn(ajaxURL);
				} else {
					// 执行 Validator 默认的ajax验证方法
					var dataSend = {};
					dataSend[ajaxParam] = ajaxData;
					result.status = RESULT_PENDING;
					jQuery
							.ajax({
								url : ajaxURL,
								dataType : 'JSON',
								// data:{ ajaxParam : ajaxData},
								data : dataSend,
								cache : false,
								success : function(data) {
									// var isPass =
									// jQuery.JSON.parse(data).exist === 'false'
									// ? true : false, msg = '';
									// var data = jQuery.evalJSON(response);
									var isPass = !data.checkResult, msg = '';
									if (isPass) {
										result.status = RESULT_PASS;
										msg = SUCC;
									} else {
										result.status = RESULT_ERROR;
										msg = option.error
												|| TIPS[option.rule].error;
									}
									validator.renderResult.call(validator,
											option, msg);
									// validator.styleValidate.call(validator,
									// item, msg, tipCnt);
								},
								error : function(xhr) {
									var msg = '错误代码：' + xhr.status;
									result.status = RESULT_ERROR;
									validator.renderResult.call(validator,
											option, msg);
									// validator.showTip.call(validator, item,
									// msg, tipCnt);
								}
							});
				}
			}
		},
		/**
		 * 获得验证项的DOM节点
		 * 
		 * @method getItem
		 * @static
		 * @param {Object}
		 *            option
		 * @return {HTMLElement}
		 */
		getItem : function(option) {
			var item = null, id = option.target;

			item = $('#' + id)[0];

			return item;
		},
		/**
		 * 获得验证提示的DOM节点
		 * 
		 * @method getItem
		 * @static
		 * @param {Object}
		 *            option
		 * @return {HTMLElement}
		 */
		getTipCnt : function(option) {
			var tipCnt = null, id = option.tipCnt;

			tipCnt = $('#' + id)[0];

			return tipCnt;
		},
		/**
		 * 获得验证提示框显示参照的DOM节点
		 * 
		 * @method getItem
		 * @static
		 * @param {Object}
		 *            option
		 * @return {HTMLElement}
		 */
		getBoxCnt : function(option) {
			var boxCnt = null, id = option.boxCnt;

			boxCnt = $('#' + id)[0];

			return boxCnt;
		},
		/**
		 * 获得表单
		 * 
		 * @method getForm
		 * @static
		 * @return {HTMLElement|Boolean}
		 */
		getForm : function() {
			if (this.form) {
				return this.form;
			} else {
				return false;
			}
		},
		/**
		 * 根据target获得Option
		 * 
		 * @method getForm
		 * @static
		 * @return {HTMLElement|Boolean}
		 */
		getOptionByTarget : function(target) {
			var options = this.options, i = 0, len = 0;

			for (len = options.length; i < len; i += 1) {
				if (options[i].target == target) {
					return options[i];
				}
			}
			return null;
		}
	};
})();