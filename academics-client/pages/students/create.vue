<template>
	<div>
		<b-container>
			<h1 class="mb-4 mt-4">New Student</h1>
			<form @submit.prevent="create" :disabled="!isFormValid">
				<b-row class="mb-3">
					<b-col><strong>Username:</strong></b-col>
					<b-col><b-input v-model.trim="student.username" :state="isUsernameValid" placeholder="Enter your username" required/></b-col>
				</b-row>
				<b-row class="mb-3">
					<b-col><strong>Password:</strong></b-col>
					<b-col><b-input v-model="student.password" type="password" :state="isPasswordValid" placeholder="Enter your password" required/></b-col>
				</b-row>
				<b-row class="mb-3">
					<b-col><strong>Name:</strong></b-col>
					<b-col><b-input v-model.trim="student.name" :state="isNameValid" placeholder="Enter your name" required/></b-col>
				</b-row>
				<b-row class="mb-3">
					<b-col><strong>Email:</strong></b-col>
					<b-col><b-input ref="email" v-model.trim="student.email" type="email" pattern=".+@my.ipleiria.pt" placeholder="Enter your e-mail" :state="isEmailValid" required/></b-col>
				</b-row>
				<b-row class="mb-3">
					<b-col><strong>Course Code:</strong></b-col>
					<b-col>
						<b-select v-model="student.courseCode" :options="courses" :state="isCourseValid" required value-field="code" text-field="name">
							<template v-slot:first>
								<option :value="null" disabled>
									-- Please select the Course --
								</option>
							</template>
						</b-select>
					</b-col>
				</b-row>
				<b-row>
					<p v-show="errorMsg" class="text-danger">
						{{ errorMsg }}
					</p>
				</b-row>
				<b-row class="mb-3">
					<b-col>
						<nuxt-link :to="{ name: 'students' }">
							<button class="btn btn-warning">Return</button>
						</nuxt-link>
					</b-col>
					<b-col>
						<button type="reset" @click="errorMsg = null" class="btn btn-primary">
							RESET
						</button>
						<button @click.prevent="create" :disabled="!isFormValid" class="btn btn-success">
							CREATE
						</button>
					</b-col>
				</b-row>
			</form>
		</b-container>
	</div>
</template>

<script>
export default {
	data() {
		return {
			student: {
				username: null,
				password: null,
				name: null,
				email: null,
				courseCode: null,
			},
			courses: [],
			errorMsg: null,
		};
	},
	created() {
		this.$axios.$get("/api/courses").then((courses) => {
			this.courses = courses;
		});
	},
    computed: {
        isUsernameValid () {
            if (!this.student.username) {
                return null
            }
            let usernameLen = this.student.username.length
            if (usernameLen < 3 || usernameLen > 15) {
                return false
            }
            return true
        },
        isPasswordValid () {
            if (!this.student.password) {
                return null
            }
            let passwordLen = this.student.password.length
            if (passwordLen < 3 || passwordLen > 255) {
                return false
            }
            return true
            },
        isNameValid () {
            if (!this.student.name) {
                return null
            }
            let nameLen = this.student.name.length
            if (nameLen < 3 || nameLen > 25) {
                return false
            }
            return true
        },
        isEmailValid () {
            if (!this.student.email) {
                return null
            }
            /* asks the component if it’s valid. We don’t need to use a regex for
            the e-mail. The input field already does the job for us, because it is of type
            “email” and validates that the user writes an e-mail that belongs to the domain
            of IPLeiria.*/
            return this.$refs.email.checkValidity()
        },
        isCourseValid () {
            if (!this.student.courseCode) {
                return null
            }
            return this.courses.some(course => this.student.courseCode === course.code)
        },
        isFormValid () {
            if (! this.isUsernameValid) {
                return false
            }
            if (! this.isPasswordValid) {
                return false
            }
            if (! this.isNameValid) {
                return false
            }
            if (! this.isEmailValid) {
                return false
            }
            if (! this.isCourseValid) {
                return false
            }
            return true
        }
    },
	methods: {
		create() {
			this.$axios
				.$post("/api/students", this.student)
				.then(() => {
					this.$router.push("/students");
				})
				.catch((error) => {
					this.errorMsg = error.response.data;
				});
		},
        reset () {
            this.errorMsg = false
        },
	},
};
</script>


 <p v-show="errorMsg" class="text-danger">
 {{ errorMsg }}
 </p>
 <button type="reset" @click="errorMsg = false">
 RESET
 </button>
 
