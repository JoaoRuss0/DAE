<template>
    <div>
        <b-container>
            <h1 class="mb-4 mt-4">
                Update Subject #{{ subject['code'] }}
            </h1>
            <form @submit.prevent="create">
                <b-row class="mb-3">
                    <b-col><strong>Name:</strong></b-col>
                    <b-col><input v-model="subject['name']" type="text"/></b-col>
                </b-row>
                <b-row class="mb-3">
                    <b-col><strong>Scholar Year:</strong></b-col>
                    <b-col><input v-model="subject['scholarYear']" type="number"/></b-col>
                </b-row>
                <b-row class="mb-3">
                    <b-col><strong>Course Year:</strong></b-col>
                    <b-col><input v-model="subject['courseYear']" type="number"/></b-col>
                </b-row>
                <b-row class="mb-3">
                    <b-col><strong>Course Code:</strong></b-col>
                    <b-col>
                        <select v-model="subject.courseCode">
                            <template v-for="course in courses">
                                <option :key="course.code" :value="course.code" :selected="course.code === subject.courseCode">
                                    {{ course.name }}
                                </option>
                            </template>
                        </select>
                    </b-col>
                </b-row>
                <b-row class="mb-3">
                    <b-col>
                        <nuxt-link to="/subjects">
                            <button class="btn btn-warning">Return</button>
                        </nuxt-link>
                    </b-col>
                    <b-col>
                        <button type="reset" class="btn btn-primary">RESET</button>
                        <button @click.prevent="update" class="btn btn-light">UPDATE</button>
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
            subject: {
                'code': null,
                'name': null,
                'scholarYear': null,
                'courseYear': null,
                'courseCode': null
            },
            courses: []
        }
    },
    created () {
        this.$axios.$get('api/subjects/' + this.$route.params.code).then((subject) => {
            this.subject = subject
        })

        this.$axios.$get('api/courses/').then((courses) => {
            this.courses = courses
        })
    },
    methods: {
        update: function () {
            this.$axios.$put('api/subjects/' + this.$route.params.code, {
                'code': this.subject.id,
                'name': this.subject.name,
                'scholarYear': this.subject.scholarYear,
                'courseYear': this.subject.courseYear,
                'courseCode': this.subject.courseCode
            }).then(() =>
                this.$router.push('/subjects')
            )
        }
    }
}
</script>
