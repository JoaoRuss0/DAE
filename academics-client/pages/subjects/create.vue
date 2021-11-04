<template>
    <div>
        <b-container>
            <h1 class="mb-4 mt-4">
                New Subject
            </h1>
            <form @submit.prevent="create">
                <b-row class="mb-3">
                    <b-col><strong>Name:</strong></b-col>
                    <b-col><input v-model="name" type="text"/></b-col>
                </b-row>
                <b-row class="mb-3">
                    <b-col><strong>Code:</strong></b-col>
                    <b-col><input v-model="code" type="number"/></b-col>
                </b-row>
                <b-row class="mb-3">
                    <b-col><strong>Course Year:</strong></b-col>
                    <b-col><input v-model="courseYear" type="number"/></b-col>
                </b-row>
                <b-row class="mb-3">
                    <b-col><strong>School Year:</strong></b-col>
                    <b-col><input v-model="scholarYear" type="number"/></b-col>
                </b-row>
                <b-row class="mb-3">
                    <b-col><strong>Course Code:</strong></b-col>
                    <b-col>
                        <select v-model="courseCode">
                            <template v-for="course in courses">
                                <option :key="course.code" :value="course.code">
                                    {{ course.name }}
                                </option>
                            </template>
                        </select>
                    </b-col>
                </b-row>
                <b-row class="mb-3">
                    <b-col>
                        <nuxt-link :to="{name: 'courses'}">
                            <button class="btn btn-warning">Return</button>
                        </nuxt-link>
                    </b-col>
                    <b-col>
                        <button type="reset" class="btn btn-primary">RESET</button>
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
            courses: [],
            name: null,
            code: null,
            courseYear: null,
            scholarYear: null,
            courseCode: null
        }
    },
    created () {
        this.$axios.$get('/api/courses').then((courses) => {
            this.courses = courses;
        })
    },
    methods: {
        create () {
            this.$axios
                .$post('/api/subjects', {
                    name: this.name,
                    code: this.code,
                    courseYear: this.courseYear,
                    scholarYear: this.scholarYear,
                    courseCode: this.courseCode
                })
                .then(() => {
                    this.$router.push('/subjects')
                })
        }
    }
}
</script>
