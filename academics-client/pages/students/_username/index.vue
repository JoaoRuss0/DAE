<template>
    <div>
        <b-container fluid="sm">
            <div v-if="student">
                <h1 class="mb-4 mt-4">
                    Student "{{ student['username'] }}"
                </h1>
                <b-row class="mb-3">
                    <b-col><strong>Username:</strong></b-col> <b-col>{{ student['username'] }}</b-col>
                </b-row>
                <b-row class="mb-3">
                    <b-col><strong>Name:</strong></b-col> <b-col>{{ student['name'] }}</b-col>
                </b-row>
                <b-row class="mb-3">
                    <b-col><strong>Password:</strong></b-col> <b-col>{{ student['password'] }}</b-col>
                </b-row>
                <b-row class="mb-3">
                    <b-col><strong>Email:</strong></b-col> <b-col>{{ student['email'] }}</b-col>
                </b-row>

                <h3 class="mb-4 mt-4">
                    Student's Course:
                </h3>
                <b-row class="mb-3">
                    <b-col><strong>Code:</strong></b-col> <b-col>{{ student['courseCode'] }}</b-col>
                </b-row>
                <b-row class="mb-3">
                    <b-col><strong>Name:</strong></b-col> <b-col>{{ student['courseName'] }}</b-col>
                </b-row>

                <h3 class="mb-4 mt-4">
                    Student's Subjects:
                </h3>
                <b-table v-if="student['subjects'].length" striped over :items="student['subjects']" :fields="subjectFields" />
                <div v-else>
                    <p class="mb-4 mt-4 text-danger">Student is enrolled in 0 subjects.</p>
                </div>

                <b-row class="mb-3">
                    <b-col>
                        <nuxt-link to="/students">
                            <button class="btn btn-warning">Return</button>
                        </nuxt-link>
                    </b-col>
                </b-row>

                <h4>Documents</h4>
                <b-table v-if="documents.length" striped over :items="documents" :fields="documentsFields">
                    <template v-slot:cell(actions)="row">
                        <b-btn class="btn btn link" @click.prevent="download(row.item)" target="_blank">Download</b-btn>
                    </template>
                </b-table>
                <p v-else>No documents.</p>
            </div>
        </b-container>
    </div>
</template>

<script>
export default {
    data () {
        return {
            student: null,
            subjectFields: ['code', 'name', 'courseCode', 'courseYear', 'scholarYear'],
            documentsFields: [ 'filename', 'actions' ]
        }
    },
    created () {
        this.$axios.$get('api/students/' + this.username).then((student) => {
            this.student = student
        })
    },
    computed: {
        username() {
            return this.$route.params.username
        },
        subjects() {
            return this.student.subjects || []
        },
        documents() {
            return this.student.documents || []
        },
    },
    methods: {
        download(fileToDownload) {
            const documentId = fileToDownload.id

            this.$axios.$get('/api/documents/download/' + documentId, {responseType: 'arraybuffer'}).then(file => {
                const url = window.URL.createObjectURL(new Blob([file]))
                const link = document.createElement('a')
                link.href = url
                link.setAttribute('download', fileToDownload.filename)
                document.body.appendChild(link)
                link.click()
            })
        }
    }
}
</script>
