<template>
    <div>
        <b-container>
            <h1 class="mb-4 mt-4">
                New Student
            </h1>
            <form @submit.prevent="create">
                <b-row class="mb-3">
                    <b-col><strong>Username:</strong></b-col>
                    <b-col><input v-model="student.username" type="text"/></b-col>
                </b-row>
                <b-row class="mb-3">
                    <b-col><strong>Password:</strong></b-col>
                    <b-col><input v-model="student.password" type="password"/></b-col>
                </b-row>
                <b-row class="mb-3">
                    <b-col><strong>Name:</strong></b-col>
                    <b-col><input v-model="student.name" type="text"/></b-col>
                </b-row>
                <b-row class="mb-3">
                    <b-col><strong>Email:</strong></b-col>
                    <b-col><input v-model="student.email" type="email"/></b-col>
                </b-row>
                <b-row class="mb-3">
                    <b-col><strong>Course Code:</strong></b-col>
                    <b-col>
                        <select v-model="student.courseCode">
                            <template v-for="course in courses">
                                <option :key="course.code" :value="course.code">
                                    {{ course.name }}
                                </option>
                            </template>
                        </select>
                    </b-col>
                </b-row>
                <b-row>
                    <p v-show="errorMsg" class="text-danger">
                        {{ errorMsg }}
                    </p>
                </b-row>
                <b-row class="mb-3">
                    <b-col>
                        <nuxt-link :to="{name: 'students'}">
                            <button class="btn btn-warning">Return</button>
                        </nuxt-link>
                    </b-col>
                    <b-col>
                        <button type="reset" @click="errorMsg = null" class="btn btn-primary">RESET</button>
                        <button @click.prevent="create" class="btn btn-success">CREATE</button>
                    </b-col>
                </b-row>
            </form>
        </b-container>
    </div>
</template>

<script>
export default {
    data () {
        return {
            student: {
                username: null,
                password: null,
                name: null,
                email: null,
                courseCode: null,
            },
            courses: [],
            errorMsg: null
        }
    },
    created () {
        this.$axios.$get('/api/courses').then((courses) => {
            this.courses = courses
        })
    },

    methods: {
        create () {
            this.$axios.$post('/api/students', this.student)
                .then(() => {
                    this.$router.push('/students')
                })
                .catch((error) => {
                    this.errorMsg = error.response.data
                })
        }
    }
}
</script>


 <p v-show="errorMsg" class="text-danger">
 {{ errorMsg }}
 </p>
 <button type="reset" @click="errorMsg = false">
 RESET
 </button>
 
