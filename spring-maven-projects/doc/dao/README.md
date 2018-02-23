# dao层的设计

### mapper  
分页、条件查询登list型的统一传入map  
	int countByCondition(Map<String, Object> params);  
    
    List<Qrcode> listByCondition(Map<String, Object> params);  

### mapping  
然后现在xml里分段sql
	<sql id="Base_Condition_Query_Sql">
		<if test="unid != null">
			and unid = #{unid,jdbcType=VARCHAR}
		</if>
		<if test="name != null">
			and name = #{name,jdbcType=VARCHAR}
		</if>
		<if test="level != null">
			and level = #{level,jdbcType=SMALLINT}
		</if>
		<if test="enable != null">
			and enable = #{enable,jdbcType=BIT}
		</if>
	</sql>
	<sql id="Page_Condition_Query_Sql">
		<if test="order != null">
			order by ${order} ${sort}
		</if>
		<if test="limit != null">
			limit #{limit,jdbcType=INTEGER}
		</if>
		<if test="offset != null">
			offset #{offset,jdbcType=INTEGER}
		</if>
	</sql>
	
	<select id="countByCondition" resultType="int" parameterType="java.util.Map">
		select
		count(1)
		from tbl_qrcode
		where deleted = 0
		<include refid="Base_Condition_Query_Sql" />
	</select>
	<select id="listByCondition" resultMap="BaseResultMap" parameterType="java.util.Map">
		select
		<include refid="Base_Column_List" />
		from tbl_qrcode
		where deleted = 0
		<include refid="Base_Condition_Query_Sql" />
		<include refid="Page_Condition_Query_Sql" />
	</select>
 

