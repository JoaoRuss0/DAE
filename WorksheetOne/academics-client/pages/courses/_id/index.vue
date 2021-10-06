<template>
    <div>
        <b-container fluid="sm">
            <div v-if="this.course">
                <h1 class="mb-4 mt-4">
                    Course #{{ this.course['code'] }}
                </h1>
                <b-row v-for="(value, name) in course" class="mb-3">
                    <b-col><strong>{{ name.charAt(0).toUpperCase() + name.slice(1) + ':' }}</strong></b-col>
                    <b-col>{{ value }}</b-col>
                </b-row>
            </div>
        </b-container>
    </div>
</template>

<script>
export default {
    data () {
        return {
            course: null
        }
    },
    created () {
        this.$axios.$get('api/courses/' + this.$route.params.id).then((course) => {
            this.course = course
        })
    },
}
</script>
